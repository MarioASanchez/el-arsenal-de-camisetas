package com.elarsenaldecamisetas.app.service.impl;

import com.elarsenaldecamisetas.app.dto.ImageDTO;
import com.elarsenaldecamisetas.app.entity.Image;
import com.elarsenaldecamisetas.app.entity.Product;
import com.elarsenaldecamisetas.app.exception.ResourceNotFoundException;
import com.elarsenaldecamisetas.app.mapper.ImageMapper;
import com.elarsenaldecamisetas.app.repository.ImageRepository;
import com.elarsenaldecamisetas.app.repository.ProductRepository;
import com.elarsenaldecamisetas.app.service.ImageService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.elarsenaldecamisetas.app.service.GoogleDriveService;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    private final ImageMapper imageMapper;
    private final GoogleDriveService googleDriveService;

    @Override
    @Transactional(readOnly = true)
    public List<ImageDTO> findByProductId(Long productId) {
        return imageRepository.findByProductId(productId).stream()
                .map(imageMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public ImageDTO create(ImageDTO imageDTO) {
        Image image = imageMapper.toEntity(imageDTO);
        Product product = productRepository.findById(imageDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", imageDTO.getProductId()));
        image.setProduct(product);
        Image saved = imageRepository.save(image);
        return imageMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!imageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Image", "id", id);
        }
        imageRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ImageDTO upload(MultipartFile file, Long productId, boolean mainImage) {
        String url = googleDriveService.uploadImage(file);
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setUrl(url);
        imageDTO.setProductId(productId);
        imageDTO.setMainImage(mainImage);
        return create(imageDTO);
    }
}
