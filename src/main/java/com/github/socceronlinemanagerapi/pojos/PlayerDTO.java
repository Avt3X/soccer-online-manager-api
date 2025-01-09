package com.github.socceronlinemanagerapi.pojos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PlayerDTO {
    private String firstName;
    private String lastName;
    private int age;
    private PlayerPosition position;
    private BigDecimal marketValue;
}
