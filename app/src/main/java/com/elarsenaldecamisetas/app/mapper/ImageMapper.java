package com.elarsenaldecamisetas.app.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.elarsenaldecamisetas.app.dto.ImageDTO;
import com.elarsenaldecamisetas.app.entity.Image;

@Component
public class ImageMapper {
    private final ModelMapper mapper = new ModelMapper();

    public ImageMapper() {
        mapper.typeMap(Image.class, ImageDTO.class).addMappings(m -> {
            m.map(src -> src.getProduct().getId(), ImageDTO::setProductId);
        });
    }

    public ImageDTO toDTO(Image image){
        return mapper.map(image, ImageDTO.class);
    }

    public Image toEntity(ImageDTO imageDTO){
        return mapper.map(imageDTO, Image.class);
    }

}
