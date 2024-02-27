package com.mdina.location.dao.repositories;

import com.mdina.location.dao.entities.Category;
import com.mdina.location.dao.entities.TouristSite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c.touristSites from Category c where c.id = ?1")
    Page<TouristSite> getTouristSites(Long id, Pageable pageable);
}
