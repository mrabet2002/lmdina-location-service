package com.mdina.location.dao.repositories;

import com.mdina.location.dao.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
