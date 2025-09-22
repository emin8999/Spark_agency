package com.sparkagency.cloudinary;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    
    private static final Logger logger = Logger.getLogger(CloudinaryService.class.getName());
    
    private final Cloudinary cloudinary;
    
    public String uploadImage(MultipartFile file) throws IOException {
        Map<String, Object> uploadOptions = ObjectUtils.asMap(
                "resource_type", "image",
                "folder", "smm_cms/images"
        );
        
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadOptions);
        return (String) uploadResult.get("secure_url");
    }
    
    public String uploadVideo(MultipartFile file) throws IOException {
        Map<String, Object> uploadOptions = ObjectUtils.asMap(
                "resource_type", "video",
                "folder", "smm_cms/videos"
        );
        
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadOptions);
        return (String) uploadResult.get("secure_url");
    }
    
    public String uploadBase64Image(String base64Data) throws IOException {
        // Base64 string formatını təmizləyirik
        String cleanBase64 = base64Data;
        if (base64Data.contains("data:")) {
            cleanBase64 = base64Data.split(",")[1];
        }
        
        Map<String, Object> uploadOptions = ObjectUtils.asMap(
                "resource_type", "image",
                "folder", "smm_cms/images"
        );
        
        Map<String, Object> uploadResult = cloudinary.uploader().upload("data:image/jpeg;base64," + cleanBase64, uploadOptions);
        return (String) uploadResult.get("secure_url");
    }
    
    public String uploadBase64Video(String base64Data) throws IOException {
        String cleanBase64 = base64Data;
        if (base64Data.contains("data:")) {
            cleanBase64 = base64Data.split(",")[1];
        }
        
        Map<String, Object> uploadOptions = ObjectUtils.asMap(
                "resource_type", "video",
                "folder", "smm_cms/videos"
        );
        
        Map<String, Object> uploadResult = cloudinary.uploader().upload("data:video/mp4;base64," + cleanBase64, uploadOptions);
        return (String) uploadResult.get("secure_url");
    }
    
    public void deleteFile(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            logger.info("Deleted file with public ID: " + publicId);
        } catch (IOException e) {
            logger.severe("Error deleting file: " + e.getMessage());
        }
    }
    
    public String getPublicIdFromUrl(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }
        
        
        // Format: https://res.cloudinary.com/cloud_name/resource_type/upload/public_id.extension
        String[] parts = url.split("/");
        if (parts.length >= 2) {
            String fileName = parts[parts.length - 1];
         
            return fileName.split("\\.")[0];
        }
        return null;
    }
}