package com.elarsenaldecamisetas.app.service;

import com.elarsenaldecamisetas.app.dto.ImageDTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public List<ImageDTO> findByProductId(Long productId);
    public ImageDTO create(ImageDTO imageDTO);
    public void delete(Long id);
    public ImageDTO upload(MultipartFile file, Long productId, boolean mainImage);
}
