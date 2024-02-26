package com.mdina.location.service.services.implementations;

import com.mdina.location.dao.entities.Category;
import com.mdina.location.dao.repositories.CategoryRepository;
import com.mdina.location.service.dto.CategoryDto;
import com.mdina.location.service.dto.CreateCategory;
import com.mdina.location.service.services.interfaces.ICategoryService;
import com.mdina.location.service.mappers.CategoryMapper;
import com.mdina.location.service.services.interfaces.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final IFileService fileService;
    private final CategoryMapper mapper;

    @Override
    public List<CategoryDto> getAll() {
        return mapper.toDtoList(categoryRepository.findAll());
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Category not found")
        );
    }

    @Override
    public Category create(CreateCategory createCategory) {
        Category category = mapper.toEntity(createCategory);
        fileService.createFile(createCategory.getImage());
        return categoryRepository.save(category);
    }

    @Override
    public void update(Long id, Category category) {

    }

    @Override
    public void delete(Long id) {

    }
}
