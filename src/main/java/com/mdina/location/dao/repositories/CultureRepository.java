package com.mdina.location.dao.repositories;

import com.mdina.location.dao.entities.Culture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CultureRepository extends JpaRepository<Culture, Long> {
}
