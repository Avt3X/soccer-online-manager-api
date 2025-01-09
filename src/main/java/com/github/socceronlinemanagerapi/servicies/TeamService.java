package com.github.socceronlinemanagerapi.servicies;

import com.github.socceronlinemanagerapi.db.repository.TeamRepository;
import com.github.socceronlinemanagerapi.db.repository.UserRepository;
import com.github.socceronlinemanagerapi.api.request.UpdateTeamRequest;
import com.github.socceronlinemanagerapi.db.entity.Team;
import com.github.socceronlinemanagerapi.db.entity.User;
import com.github.socceronlinemanagerapi.pojos.TeamDTO;
import com.github.socceronlinemanagerapi.mapper.TeamMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final TeamMapper teamMapper;

    public TeamService(TeamRepository teamRepository, UserRepository userRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.teamMapper = teamMapper;
    }

    public TeamDTO getTeamDetails(String userEmail) {
        return teamMapper.mapToTeamDTO(this.getUserTeam(userEmail));
    }

    @Transactional
    public TeamDTO updateTeamDetails(String userEmail, UpdateTeamRequest request) {
        var team = this.getUserTeam(userEmail);
        Optional.ofNullable(request.getName()).ifPresent(team::setName);
        Optional.ofNullable(request.getCountry()).ifPresent(team::setCountry);
        return teamMapper.mapToTeamDTO(teamRepository.save(team));
    }

    private Team getUserTeam(String userEmail) {
        return userRepository.findUserByEmail(userEmail).map(User::getTeam)
                .orElseThrow(() -> new UsernameNotFoundException("Unable to get user team information: " + userEmail));

    }
}
