package com.github.socceronlinemanagerapi.mapper;

import com.github.socceronlinemanagerapi.db.entity.Team;
import com.github.socceronlinemanagerapi.pojos.TeamDTO;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    private final PlayerMapper playerMapper;

    public TeamMapper(PlayerMapper playerMapper) {
        this.playerMapper = playerMapper;
    }

    public TeamDTO mapToTeamDTO(Team team) {
        return TeamDTO.builder()
                .name(team.getName())
                .country(team.getCountry())
                .budget(team.getBudget())
                .teamValue(team.getTeamValue())
                .players(team.getPlayers().stream().map(playerMapper::mapToPlayerDTO).toList())
                .build();
    }
}
