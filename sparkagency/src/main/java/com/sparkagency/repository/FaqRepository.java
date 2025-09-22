package com.sparkagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparkagency.entity.FaqEntity;

@Repository
public interface FaqRepository extends JpaRepository<FaqEntity, Long> {
}
