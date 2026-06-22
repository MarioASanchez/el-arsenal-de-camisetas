package com.elarsenaldecamisetas.app.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private Long id;
    private Long quantity;
    private String size;
    private BigDecimal price;
    private Long orderId;
    private Long productId;
}
