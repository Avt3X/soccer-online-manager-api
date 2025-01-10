package com.github.socceronlinemanagerapi.pojos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PlayerDTO {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private PlayerPosition position;
    private PlayerStatus status;
    private BigDecimal marketValue;
}
