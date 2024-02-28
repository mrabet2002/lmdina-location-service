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
@RequestMapping("${api.version}/test")
@RequiredArgsConstructor
public class TestController {
    @GetMapping
    public String getPage() {
        return "Hello";
    }
}
