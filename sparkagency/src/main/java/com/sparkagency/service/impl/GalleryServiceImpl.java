package com.sparkagency.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sparkagency.cloudinary.CloudinaryService;
import com.sparkagency.dto.GalleryDto;
import com.sparkagency.entity.GalleryEntity;
import com.sparkagency.repository.GalleryRepository;
import com.sparkagency.service.GalleryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {
    
   
    private final GalleryRepository galleryRepository;
    

    private final CloudinaryService cloudinaryService;
    
    @Override
    public List<GalleryDto> getAllGallery() {
        return galleryRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public GalleryDto createGalleryItem(GalleryDto galleryDTO) {
        GalleryEntity gallery = GalleryEntity.builder()
                .type(GalleryEntity.MediaType.valueOf(galleryDTO.getType().toUpperCase()))
                .src(galleryDTO.getSrc())
                .alt(galleryDTO.getAlt())
                .cloudinaryPublicId(galleryDTO.getCloudinaryPublicId())
                .build();
        
        GalleryEntity savedGallery = galleryRepository.save(gallery);
        return mapToDTO(savedGallery);
    }
    @Override
    public GalleryDto uploadImage(MultipartFile file, String alt) throws IOException {
        String imageUrl = cloudinaryService.uploadImage(file);
        String publicId = cloudinaryService.getPublicIdFromUrl(imageUrl);
        
        GalleryEntity gallery = GalleryEntity.builder()
                .type(GalleryEntity.MediaType.IMAGE)
                .src(imageUrl)
                .alt(alt)
                .cloudinaryPublicId(publicId)
                .build();
        
        GalleryEntity savedGallery = galleryRepository.save(gallery);
        return mapToDTO(savedGallery);
    }
    @Override
    public GalleryDto uploadVideo(MultipartFile file, String alt) throws IOException {
        String videoUrl = cloudinaryService.uploadVideo(file);
        String publicId = cloudinaryService.getPublicIdFromUrl(videoUrl);
        
        GalleryEntity gallery = GalleryEntity.builder()
                .type(GalleryEntity.MediaType.VIDEO)
                .src(videoUrl)
                .alt(alt)
                .cloudinaryPublicId(publicId)
                .build();
        
        GalleryEntity savedGallery = galleryRepository.save(gallery);
        return mapToDTO(savedGallery);
    }
    @Override
    public GalleryDto uploadBase64Image(String base64Data, String alt) throws IOException {
        String imageUrl = cloudinaryService.uploadBase64Image(base64Data);
        String publicId = cloudinaryService.getPublicIdFromUrl(imageUrl);
        
        GalleryEntity gallery = GalleryEntity.builder()
                .type(GalleryEntity.MediaType.IMAGE)
                .src(imageUrl)
                .alt(alt)
                .cloudinaryPublicId(publicId)
                .build();
        
        GalleryEntity savedGallery = galleryRepository.save(gallery);
        return mapToDTO(savedGallery);
    }
    @Override
    public GalleryDto uploadBase64Video(String base64Data, String alt) throws IOException {
        String videoUrl = cloudinaryService.uploadBase64Video(base64Data);
        String publicId = cloudinaryService.getPublicIdFromUrl(videoUrl);
        
        GalleryEntity gallery = GalleryEntity.builder()
                .type(GalleryEntity.MediaType.VIDEO)
                .src(videoUrl)
                .alt(alt)
                .cloudinaryPublicId(publicId)
                .build();
        
        GalleryEntity savedGallery = galleryRepository.save(gallery);
        return mapToDTO(savedGallery);
    }
    @Override
    public void deleteGalleryItem(Long id) {
        GalleryEntity gallery = galleryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gallery item not found"));
        
        if (gallery.getCloudinaryPublicId() != null) {
            cloudinaryService.deleteFile(gallery.getCloudinaryPublicId());
        }
        
        galleryRepository.deleteById(id);
    }
    
    private GalleryDto mapToDTO(GalleryEntity gallery) {
        return GalleryDto.builder()
                .id(gallery.getId())
                .type(gallery.getType().name().toLowerCase())
                .src(gallery.getSrc())
                .alt(gallery.getAlt())
                .cloudinaryPublicId(gallery.getCloudinaryPublicId())
                .build();
    }
}