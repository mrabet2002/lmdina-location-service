package com.mdina.location.service.mappers;

import com.mdina.location.dao.entities.TouristSite;
import com.mdina.location.service.dto.CreateTouristSite;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TouristSiteMapper {
    TouristSite toEntity(CreateTouristSite createTouristSite);
}
