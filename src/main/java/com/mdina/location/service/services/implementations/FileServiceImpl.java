package com.mdina.location.service.services.implementations;

import com.mdina.location.config.FileConfig;
import com.mdina.location.dao.entities.File;
import com.mdina.location.dao.repositories.FileRepository;
import com.mdina.location.enumerations.FileType;
import com.mdina.location.exceptions.UploadFileException;
import com.mdina.location.service.services.interfaces.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements IFileService {

    private final FileConfig fileConfig;
    private final FileRepository fileRepository;

    @Override
    public File uploadFile(MultipartFile multipartFile) {
        try {
            // Get the file's original filename
            String originalFilename = multipartFile.getOriginalFilename();

            // Generate a new file name with the same extension
            String newFileName = generateFileName(originalFilename);

            // Get the bytes of the file
            byte[] bytes = multipartFile.getBytes();

            // Write the file to your local file system and get the path
            Path path = writeToFileSystem(bytes, newFileName);

            // Get the file type
            String mimeType = multipartFile.getContentType();

            // Map the MIME type to FileType
            FileType fileType = mapMimeTypeToFileType(mimeType);

            // Create a new File object
            File file = new File();
            file.setUrl(path.toString());
            file.setType(fileType); // Set the file type

            // Save the File object to the database
            return file;
        } catch (IOException e) {
            throw new UploadFileException();
        }
    }

    private Path writeToFileSystem(byte[] bytes, String newFileName) throws IOException {
        Path path = Paths.get(fileConfig.getUploadPath() + newFileName);
        Files.write(path, bytes);
        return path;
    }

    private FileType mapMimeTypeToFileType(String mimeType) {
        switch (mimeType) {
            case "image/jpeg":
            case "image/png":
            case "image/gif":
                return FileType.IMAGE;
            case "video/mp4":
            case "video/mpeg":
                return FileType.VIDEO;
            case "audio/mpeg":
            case "audio/ogg":
                return FileType.AUDIO;
            default:
                return FileType.DOCUMENT;
        }
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private String generateFileName(String originalFilename) {
        return UUID.randomUUID() + fileConfig.getSuffix() + "." + getFileExtension(originalFilename);
    }

    @Override
    public List<File> createFiles(List<MultipartFile> multipartFiles) {
        fileRepository.saveAll(multipartFiles.stream().map(
                multipartFile -> {
                        return uploadFile(multipartFile);
                }
        ).collect(Collectors.toList()));
        return null;
    }

    @Override
    public File createFile(MultipartFile file) {
        return fileRepository.save(uploadFile(file));
    }

    @Override
    public void deleteFile(Long id) {
        File file = fileRepository.findById(id).orElseThrow(
                () -> new RuntimeException("File not found")
        );
        Path path = Paths.get(file.getUrl());
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFile(Long id, byte[] file) {

    }

    @Override
    public File getFile(Long id) {
        return fileRepository.findById(id).orElseThrow(
                () -> new RuntimeException("File not found")
        );
    }
}
