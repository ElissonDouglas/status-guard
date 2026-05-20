package com.github.elissondouglas.status_guard.repository;

import com.github.elissondouglas.status_guard.entities.Website;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsiteRepository extends JpaRepository<Website, Long> {
}
