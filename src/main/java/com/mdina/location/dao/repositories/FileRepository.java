package com.mdina.location.dao.repositories;

import com.mdina.location.dao.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
