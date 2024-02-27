package com.mdina.location.service.services.implementations;

import com.mdina.location.dao.entities.Calendar;
import com.mdina.location.dao.repositories.CalendarRepository;
import com.mdina.location.exceptions.BadRequestException;
import com.mdina.location.exceptions.RecordNotFoundException;
import com.mdina.location.service.services.interfaces.ICalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements ICalendarService {

    private final CalendarRepository calendarRepository;

    @Override
    public List<Calendar> getAll() {
        return calendarRepository.findAll();
    }

    @Override
    public Calendar getById(Long id) {
        return calendarRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("Calendar not found")
        );
    }

    @Override
    public Calendar create(Calendar calendar) {
        if (calendar.getStartAt().isAfter(calendar.getEndAt()))
            throw new BadRequestException("Start date cannot be after end date");
        return calendarRepository.save(calendar);
    }

    @Override
    public Calendar update(Long id, Calendar calendar) {
        this.checkIfExists(id);
        calendar.setId(id);
        return calendarRepository.save(calendar);
    }

    @Override
    public void checkIfExists(Long id) {
        if (!calendarRepository.existsById(id))
            throw new RecordNotFoundException("Calendar not found");
    }

    @Override
    public void delete(Long id) {
        calendarRepository.deleteById(id);
    }
}
