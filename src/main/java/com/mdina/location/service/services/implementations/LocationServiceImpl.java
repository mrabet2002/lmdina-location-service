package com.mdina.location.service.services.implementations;

import com.mdina.location.dao.entities.Location;
import com.mdina.location.dao.repositories.LocationRepository;
import com.mdina.location.service.services.interfaces.ILocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements ILocationService {

    private final LocationRepository locationRepository;

    @Override
    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location getById(Long id) {
        return locationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Tourist site not found")
        );
    }

    @Override
    @Transactional
    public Location create(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location update(Long id, Location location) {
        this.checkIfExists(id);
        location.setId(id);
        return locationRepository.save(location);
    }

    @Override
    public void delete(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public void checkIfExists(Long id) {
        locationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Location not found")
        );
    }
}
