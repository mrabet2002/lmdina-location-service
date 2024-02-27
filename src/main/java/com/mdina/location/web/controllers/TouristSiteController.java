package com.mdina.location.web.controllers;

import com.mdina.location.dao.entities.TouristSite;
import com.mdina.location.service.dto.CreateTouristSite;
import com.mdina.location.service.services.interfaces.ITouristService;
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
@RequestMapping("${api.version}/tourist-sites")
@RequiredArgsConstructor
public class TouristSiteController {
    private final ITouristService touristService;

    @GetMapping
    public ResponseEntity<Page<TouristSite>> getPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(touristService.getPage(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TouristSite> getById(@PathVariable Long id) {
        return ResponseEntity.ok(touristService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TouristSite> create(@RequestBody CreateTouristSite touristSite) {
        return ResponseEntity.ok(touristService.create(touristSite));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TouristSite> update(@PathVariable Long id, @RequestBody TouristSite touristSite) {
        return ResponseEntity.ok(touristService.update(id, touristSite));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        touristService.delete(id);
        return ResponseEntity.ok().build();
    }
}
