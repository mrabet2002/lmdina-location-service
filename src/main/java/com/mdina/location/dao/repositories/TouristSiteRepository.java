package com.mdina.location.dao.repositories;

import com.mdina.location.dao.entities.TouristSite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TouristSiteRepository extends JpaRepository<TouristSite, Long> {
}
