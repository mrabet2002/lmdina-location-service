package com.mdina.location.service.services.interfaces;

import com.mdina.location.dao.entities.Culture;
import com.mdina.location.service.dto.CreateCulture;
import org.springframework.data.domain.Page;

public interface ICultureService {
    Page<Culture> getPage(int page, int size);
    Culture getById(Long id);
    Culture create(CreateCulture createCulture);
    Culture update(Long id, Culture culture);
    void delete(Long id);
}
