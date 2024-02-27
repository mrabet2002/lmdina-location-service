package com.mdina.location.service.services.implementations;

import com.mdina.location.dao.entities.Category;
import com.mdina.location.dao.entities.TouristSite;
import com.mdina.location.dao.repositories.CategoryRepository;
import com.mdina.location.exceptions.RecordNotFoundException;
import com.mdina.location.service.dto.CategoryDto;
import com.mdina.location.service.dto.CreateCategory;
import com.mdina.location.service.services.interfaces.ICategoryService;
import com.mdina.location.service.mappers.CategoryMapper;
import com.mdina.location.service.services.interfaces.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public CategoryDto getById(Long id) {
        return mapper.toDto(categoryRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("Category not found")
        ));
    }

    @Override
    public Category create(CreateCategory createCategory) {
        Category category = mapper.toEntity(createCategory);
        category.setImage(
                fileService.uploadToFileSystem(createCategory.getImage()));
        return categoryRepository.save(category);
    }

    @Override
    public void update(Long id, CreateCategory category) {
        Category categoryToUpdate = categoryRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("Category not found")
        );
        categoryToUpdate.setName(category.getName());
        categoryToUpdate.setDescription(category.getDescription());
        categoryToUpdate.setImage(
                fileService.uploadToFileSystem(category.getImage()));
        categoryRepository.save(categoryToUpdate);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<TouristSite> getTouristSites(Long id, int page, int size) {
        return categoryRepository.getTouristSites(id, PageRequest.of(page, size));
    }
}
