package com.sparkagency.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sparkagency.dto.GalleryDto;

public interface GalleryService {

    List<GalleryDto> getAllGallery();

    GalleryDto createGalleryItem(GalleryDto galleryDTO);

    GalleryDto uploadImage(MultipartFile file, String alt) throws IOException;

    GalleryDto uploadVideo(MultipartFile file, String alt) throws IOException;

    GalleryDto uploadBase64Image(String base64Data, String alt) throws IOException;

    GalleryDto uploadBase64Video(String base64Data, String alt) throws IOException;

    void deleteGalleryItem(Long id);

}
