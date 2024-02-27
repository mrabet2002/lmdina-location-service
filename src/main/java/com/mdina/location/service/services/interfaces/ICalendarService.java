package com.mdina.location.service.services.interfaces;

import com.mdina.location.dao.entities.Calendar;

import java.util.List;

public interface ICalendarService {
    List<Calendar> getAll();
    Calendar getById(Long id);
    Calendar create(Calendar calendar);
    Calendar update(Long id, Calendar calendar);

    void checkIfExists(Long id);

    void delete(Long id);
}
