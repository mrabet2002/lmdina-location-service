package com.mdina.location.web.controllers;

import com.mdina.location.dao.entities.File;
import com.mdina.location.dao.entities.File;
import com.mdina.location.service.services.interfaces.IFileService;
import com.mdina.location.service.services.interfaces.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${api.version}/files")
@RequiredArgsConstructor
public class FileController {
    private final IFileService fileService;

    @GetMapping("/{id}")
    public ResponseEntity<File> getById(@PathVariable Long id) {
        return ResponseEntity.ok(fileService.getFile(id));
    }

    @PostMapping
    public ResponseEntity<File> create(@ModelAttribute MultipartFile file) {
        return ResponseEntity.ok(fileService.createFile(file));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @ModelAttribute MultipartFile file) {
        fileService.updateFile(id, file);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fileService.deleteFile(id);
        return ResponseEntity.ok().build();
    }
}
