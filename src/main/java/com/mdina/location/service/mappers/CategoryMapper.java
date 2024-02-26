package com.mdina.location.service.mappers;

import com.mdina.location.dao.entities.Category;
import com.mdina.location.service.dto.CategoryDto;
import com.mdina.location.service.dto.CreateCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDto categoryDto);
    CategoryDto toDto(Category category);
    @Mapping(target = "image", ignore = true)
    Category toEntity(CreateCategory categoryDto);
    List<CategoryDto> toDtoList(List<Category> categories);
}
