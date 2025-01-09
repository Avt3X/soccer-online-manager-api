package com.github.socceronlinemanagerapi.api.request;

import lombok.Data;

@Data
public class UpdateTeamRequest {
    private String name;
    private String country;
}
