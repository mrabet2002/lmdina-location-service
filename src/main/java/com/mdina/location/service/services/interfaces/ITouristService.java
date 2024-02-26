package com.mdina.location.service.services.interfaces;

import com.mdina.location.dao.entities.TouristSite;
import com.mdina.location.service.dto.CreateTouristSite;

import java.util.List;

public interface ITouristService {
    List<TouristSite> getAll();
    TouristSite getById(Long id);
    TouristSite create(CreateTouristSite createTouristSite);
    TouristSite update(Long id, TouristSite touristSite);
    void delete(Long id);
}
