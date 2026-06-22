package com.elarsenaldecamisetas.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private LocalDate date;
    private BigDecimal totalPrice;
    private String status; 
    private String paypalOrderId;
    private Long userId;
    private String userName;
}
