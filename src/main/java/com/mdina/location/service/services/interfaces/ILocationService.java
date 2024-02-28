package com.mdina.location.service.services.interfaces;

import com.mdina.location.dao.entities.Location;

import java.util.List;

public interface ILocationService {
    List<Location> getAll();
    Location getById(Long id);
    Location create(Location location);
    Location update(Long id, Location location);
    void delete(Long id);

    void checkIfExists(Long id);
}
