package com.elarsenaldecamisetas.app.service.impl;

import com.elarsenaldecamisetas.app.dto.ProductDTO;
import com.elarsenaldecamisetas.app.entity.Product;
import com.elarsenaldecamisetas.app.entity.Team;
import com.elarsenaldecamisetas.app.exception.ResourceNotFoundException;
import com.elarsenaldecamisetas.app.mapper.ProductMapper;
import com.elarsenaldecamisetas.app.repository.ProductRepository;
import com.elarsenaldecamisetas.app.repository.TeamRepository;
import com.elarsenaldecamisetas.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final TeamRepository teamRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return productMapper.toDTO(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findByTeamId(Long teamId) {
        return productRepository.findByTeamId(teamId).stream()
                .map(productMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findByLeagueId(Long leagueId) {
        return productRepository.findByTeamLeagueId(leagueId).stream()
                .map(productMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findCurrent() {
        return productRepository.findByCurrentTrue().stream()
                .map(productMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public ProductDTO create(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Team team = teamRepository.findById(productDTO.getTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", productDTO.getTeamId()));
        product.setTeam(team);
        Product saved = productRepository.save(product);
        return productMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        product.setName(productDTO.getName());
        product.setCurrent(productDTO.isCurrent());
        product.setPrice(productDTO.getPrice());
        product.setSeason(productDTO.getSeason());
        if (productDTO.getTeamId() != null) {
            Team team = teamRepository.findById(productDTO.getTeamId())
                    .orElseThrow(() -> new ResourceNotFoundException("Team", "id", productDTO.getTeamId()));
            product.setTeam(team);
        }
        Product updated = productRepository.save(product);
        return productMapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product", "id", id);
        }
        productRepository.deleteById(id);
    }
}
