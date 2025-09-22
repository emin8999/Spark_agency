package com.sparkagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparkagency.entity.PackageEntity;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, Long> {
}