package com.mdina.location.web.controllers;

import com.mdina.location.dao.entities.Category;
import com.mdina.location.dao.entities.TouristSite;
import com.mdina.location.service.dto.CategoryDto;
import com.mdina.location.service.dto.CreateCategory;
import com.mdina.location.service.services.interfaces.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.version}/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CreateCategory category) {
        return ResponseEntity.ok(categoryService.create(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody CreateCategory category) {
        categoryService.update(id, category);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/tourist-sites")
    public ResponseEntity<Page<TouristSite>> getTouristSites(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(categoryService.getTouristSites(id, page, size));
    }
}
