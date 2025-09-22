package com.sparkagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparkagency.entity.ServiceEntity;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    
}
