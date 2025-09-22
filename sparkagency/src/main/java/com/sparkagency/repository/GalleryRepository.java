package com.sparkagency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparkagency.entity.GalleryEntity;

@Repository
public interface GalleryRepository extends JpaRepository<GalleryEntity, Long> {
  //  List<GalleryEntity> findAllByOrderByCreatedAtDesc();
}
