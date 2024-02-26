package com.mdina.location.dao.repositories;

import com.mdina.location.dao.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
