package com.mdina.location.service.mappers;

import com.mdina.location.dao.entities.Culture;
import com.mdina.location.service.dto.CreateCulture;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CultureMapper {
    Culture toEntity(CreateCulture createCulture);
}
