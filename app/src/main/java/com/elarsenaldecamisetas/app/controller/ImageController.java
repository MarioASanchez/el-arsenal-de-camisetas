package com.elarsenaldecamisetas.app.controller;

import com.elarsenaldecamisetas.app.dto.ImageDTO;
import com.elarsenaldecamisetas.app.service.ImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ImageDTO>> findByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(imageService.findByProductId(productId));
    }

    @PostMapping
    public ResponseEntity<ImageDTO> create(@Valid @RequestBody ImageDTO imageDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(imageService.create(imageDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        imageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<ImageDTO> upload(@RequestParam("file") MultipartFile file, @RequestParam("productId") Long productId, @RequestParam(value="mainImage", defaultValue="false") boolean mainImage){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(imageService.upload(file, productId, mainImage));
    }

}
