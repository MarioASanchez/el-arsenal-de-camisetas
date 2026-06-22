package com.elarsenaldecamisetas.app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    @NotNull
    private List<CreateOrderItem> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateOrderItem {
        @NotNull
        private Long productId;
        @Min(1)
        private int quantity;
        @NotBlank
        private String size;
    }
}
