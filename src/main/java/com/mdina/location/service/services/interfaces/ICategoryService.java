package com.mdina.location.service.services.interfaces;

import com.mdina.location.dao.entities.Category;
import com.mdina.location.dao.entities.TouristSite;
import com.mdina.location.service.dto.CategoryDto;
import com.mdina.location.service.dto.CreateCategory;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryService {
    List<CategoryDto> getAll();
    CategoryDto getById(Long id);
    Category create(CreateCategory category);
    void update(Long id, CreateCategory category);
    void delete(Long id);

    Page<TouristSite> getTouristSites(Long id, int page, int size);
}
