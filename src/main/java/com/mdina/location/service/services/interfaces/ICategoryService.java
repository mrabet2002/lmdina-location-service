package com.mdina.location.service.services.interfaces;

import com.mdina.location.dao.entities.Category;
import com.mdina.location.service.dto.CategoryDto;
import com.mdina.location.service.dto.CreateCategory;

import java.util.List;

public interface ICategoryService {
    List<CategoryDto> getAll();
    Category getById(Long id);
    Category create(CreateCategory category);
    void update(Long id, Category category);
    void delete(Long id);
}
