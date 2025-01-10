package com.github.socceronlinemanagerapi.servicies;

import com.github.socceronlinemanagerapi.api.request.UpdateTeamRequest;
import com.github.socceronlinemanagerapi.db.entity.Team;
import com.github.socceronlinemanagerapi.pojos.TeamDTO;

public interface TeamService {
    TeamDTO getTeamDetails(String userEmail);
    TeamDTO updateTeamDetails(String userEmail, UpdateTeamRequest request);
}
