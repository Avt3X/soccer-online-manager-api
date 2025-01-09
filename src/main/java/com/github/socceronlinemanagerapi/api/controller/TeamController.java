package com.github.socceronlinemanagerapi.api.controller;

import com.github.socceronlinemanagerapi.api.request.UpdateTeamRequest;
import com.github.socceronlinemanagerapi.pojos.TeamDTO;
import com.github.socceronlinemanagerapi.servicies.TeamService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public TeamDTO viewTeam(@AuthenticationPrincipal UserDetails userDetails) {
        return teamService.getTeamDetails(userDetails.getUsername());
    }

    @PostMapping
    public TeamDTO updateTeam(@AuthenticationPrincipal UserDetails userDetails,
                              @RequestBody UpdateTeamRequest request) {
        return teamService.updateTeamDetails(userDetails.getUsername(), request);
    }
}
