package com.github.socceronlinemanagerapi.pojos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String date;
    private String message;
}
