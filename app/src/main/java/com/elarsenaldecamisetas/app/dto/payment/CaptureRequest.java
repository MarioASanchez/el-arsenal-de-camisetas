package com.elarsenaldecamisetas.app.dto.payment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaptureRequest {
    @NotBlank
    private String paypalOrderId;
}
