package com.github.socceronlinemanagerapi.api.request;

import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
public class UpdatePlayerMarketValueRequest {
    private int playerId;
    @Positive(message = "Price must not be less than or equal to 0")
    private BigDecimal marketValue;
}
