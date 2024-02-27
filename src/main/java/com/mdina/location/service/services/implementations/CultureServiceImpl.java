package com.mdina.location.service.services.implementations;

import com.mdina.location.dao.entities.File;
import com.mdina.location.dao.entities.Location;
import com.mdina.location.dao.entities.Culture;
import com.mdina.location.dao.repositories.LocationRepository;
import com.mdina.location.dao.repositories.CultureRepository;
import com.mdina.location.service.dto.CreateCulture;
import com.mdina.location.service.mappers.CultureMapper;
import com.mdina.location.service.services.interfaces.ICultureService;
import com.mdina.location.service.services.interfaces.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CultureServiceImpl implements ICultureService {

    private final CultureRepository cultureRepository;
    private final IFileService fileService;
    private final CultureMapper mapper;

    @Override
    public Page<Culture> getPage(int page, int size) {
        return cultureRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Culture getById(Long id) {
        return cultureRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Tourist site not found")
        );
    }

    @Override
    @Transactional
    public Culture create(CreateCulture createCulture) {
        List<File> createdFiles = fileService.createFiles(createCulture.getAttachments());
        Culture culture = mapper.toEntity(createCulture);
        culture.setAttachments(createdFiles);
        return cultureRepository.save(culture);
    }

    @Override
    public Culture update(Long id, Culture culture) {
        this.checkIfExists(id);
        culture.setId(id);
        return cultureRepository.save(culture);
    }

    @Override
    public void delete(Long id) {
        cultureRepository.deleteById(id);
    }

    public void checkIfExists(Long id) {
        cultureRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Tourist site not found")
        );
    }
}
