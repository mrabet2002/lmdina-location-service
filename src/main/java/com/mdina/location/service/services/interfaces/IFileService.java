package com.mdina.location.service.services.interfaces;

import com.mdina.location.dao.entities.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileService {
    /**
     * Uploads a file to the database
     * @param file the file to be uploaded
     * @return the file name
     */
    File uploadFile(MultipartFile file);

    String uploadToFileSystem(MultipartFile multipartFile);

    List<File> createFiles(List<MultipartFile> files);
    File createFile(MultipartFile file);
    void deleteFile(Long id);
    void updateFile(Long id, byte[] file);
    File getFile(Long id);
}
