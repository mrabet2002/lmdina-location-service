package com.mdina.location.dao.repositories;

import com.mdina.location.dao.entities.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
