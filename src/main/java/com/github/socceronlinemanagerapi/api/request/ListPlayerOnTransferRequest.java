package com.github.socceronlinemanagerapi.api.request;

import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ListPlayerOnTransferRequest {
    private int playerId;
    @Positive(message = "Price must not be less than or equal to 0")
    private BigDecimal askingPrice;
}
