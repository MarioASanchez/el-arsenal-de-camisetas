package com.elarsenaldecamisetas.app.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.elarsenaldecamisetas.app.dto.ProductDTO;
import com.elarsenaldecamisetas.app.entity.Product;

@Component
public class ProductMapper {
    private final ModelMapper mapper = new ModelMapper();

    public ProductMapper() {
        mapper.typeMap(Product.class, ProductDTO.class).addMappings(m -> {
            m.map(src -> src.getTeam().getId(), ProductDTO::setTeamId);
        });

    }

    public ProductDTO toDTO(Product product) {
        return mapper.map(product, ProductDTO.class);
    }

    public Product toEntity(ProductDTO productDTO) {
        return mapper.map(productDTO, Product.class);
    }
}
