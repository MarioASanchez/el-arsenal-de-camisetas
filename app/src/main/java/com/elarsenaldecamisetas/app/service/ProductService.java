package com.elarsenaldecamisetas.app.service;

import com.elarsenaldecamisetas.app.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> findAll();
    public ProductDTO findById(Long id);
    public List<ProductDTO> findByTeamId(Long teamId);
    public List<ProductDTO> findByLeagueId(Long leagueId);
    public List<ProductDTO> findCurrent();
    public ProductDTO create(ProductDTO productDTO);
    public ProductDTO update(Long id, ProductDTO productDTO);
    public void delete(Long id);
}
