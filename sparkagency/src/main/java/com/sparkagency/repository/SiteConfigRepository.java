package com.sparkagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparkagency.entity.SiteConfig;

@Repository
public interface SiteConfigRepository extends JpaRepository<SiteConfig, Long> {
   // SiteConfig findTopByOrderByUpdatedAtDesc();
}
