package com.mdina.location.web.controllers;

import com.mdina.location.dao.entities.Culture;
import com.mdina.location.service.dto.CreateCulture;
import com.mdina.location.service.services.interfaces.ICultureService;
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

@RestController
@RequestMapping("${api.version}/cultures")
@RequiredArgsConstructor
public class CultureController {
    private final ICultureService cultureService;

    @GetMapping
    public ResponseEntity<Page<Culture>> getPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(cultureService.getPage(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Culture> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cultureService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Culture> create(@RequestBody CreateCulture culture) {
        return ResponseEntity.ok(cultureService.create(culture));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Culture> update(@PathVariable Long id, @RequestBody Culture culture) {
        return ResponseEntity.ok(cultureService.update(id, culture));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cultureService.delete(id);
        return ResponseEntity.ok().build();
    }
}
