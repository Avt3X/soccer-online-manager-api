package com.github.socceronlinemanagerapi.pojos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class TeamDTO {
    private String name;
    private String country;
    private BigDecimal budget;
    private BigDecimal teamValue;
    private List<PlayerDTO> players;
}
