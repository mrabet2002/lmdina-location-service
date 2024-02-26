package com.mdina.location.service.services.interfaces;

import com.mdina.location.dao.entities.TouristSite;

import java.util.List;

public interface ICalenderService {
    List<TouristSite> getAll();
    TouristSite getById(Long id);
    TouristSite create(TouristSite touristSite);
    TouristSite update(TouristSite touristSite);
    void delete(Long id);
}
