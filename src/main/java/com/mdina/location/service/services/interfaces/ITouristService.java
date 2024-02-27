package com.mdina.location.service.services.interfaces;

import com.mdina.location.dao.entities.TouristSite;
import com.mdina.location.service.dto.CreateTouristSite;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITouristService {
    Page<TouristSite> getPage(int page, int size);
    TouristSite getById(Long id);
    TouristSite create(CreateTouristSite createTouristSite);
    TouristSite update(Long id, TouristSite touristSite);
    void delete(Long id);
}
