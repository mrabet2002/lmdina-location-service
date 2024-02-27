package com.mdina.location.service.services.implementations;

import com.mdina.location.dao.entities.Location;
import com.mdina.location.dao.entities.TouristSite;
import com.mdina.location.dao.entities.File;
import com.mdina.location.dao.repositories.LocationRepository;
import com.mdina.location.dao.repositories.TouristSiteRepository;
import com.mdina.location.service.dto.CreateTouristSite;
import com.mdina.location.service.services.interfaces.ITouristService;
import com.mdina.location.service.services.interfaces.IFileService;
import com.mdina.location.service.mappers.TouristSiteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TouristServiceImpl implements ITouristService {

    private final TouristSiteRepository touristSiteRepository;
    private final LocationRepository locationRepository;
    private final IFileService fileService;
    private final TouristSiteMapper mapper;

    @Override
    public Page<TouristSite> getPage(int page, int size) {
        return touristSiteRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public TouristSite getById(Long id) {
        return touristSiteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Tourist site not found")
        );
    }

    @Override
    @Transactional
    public TouristSite create(CreateTouristSite createTouristSite) {
        Location location = createTouristSite.getLocation();
        if (location != null && location.getLang() != null && location.getLat() != null) {
            location = locationRepository.save(location);
            createTouristSite.setLocation(location);
        }
        List<File> createdFiles = fileService.createFiles(createTouristSite.getAttachments());
        TouristSite touristSite = mapper.toEntity(createTouristSite);
        touristSite.setAttachments(createdFiles);
        return touristSiteRepository.save(touristSite);
    }

    @Override
    public TouristSite update(Long id, TouristSite touristSite) {
        this.checkIfExists(id);
        touristSite.setId(id);
        return touristSiteRepository.save(touristSite);
    }

    @Override
    public void delete(Long id) {
        touristSiteRepository.deleteById(id);
    }

    public void checkIfExists(Long id) {
        touristSiteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Tourist site not found")
        );
    }
}
