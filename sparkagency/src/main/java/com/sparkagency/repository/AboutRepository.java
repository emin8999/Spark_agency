package com.sparkagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparkagency.entity.AboutEntity;

@Repository
public interface AboutRepository extends JpaRepository<AboutEntity, Long> {
    //AboutEntity findTopByOrderByUpdatedAtDesc();
}