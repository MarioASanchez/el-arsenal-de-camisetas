package com.elarsenaldecamisetas.app.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Long teamId;
    private boolean current;
    private BigDecimal price;
    private Long season;
    private List <ImageDTO> images;
}
