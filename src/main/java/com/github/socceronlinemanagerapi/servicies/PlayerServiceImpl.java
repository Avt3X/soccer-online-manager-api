package com.github.socceronlinemanagerapi.servicies;

import com.github.socceronlinemanagerapi.api.request.ListPlayerOnTransferRequest;
import com.github.socceronlinemanagerapi.db.entity.Player;
import com.github.socceronlinemanagerapi.db.entity.Team;
import com.github.socceronlinemanagerapi.db.repository.PlayerRepository;
import com.github.socceronlinemanagerapi.db.repository.TeamRepository;
import com.github.socceronlinemanagerapi.mapper.PlayerMapper;
import com.github.socceronlinemanagerapi.pojos.PlayerDTO;
import com.github.socceronlinemanagerapi.pojos.PlayerStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final PlayerMapper playerMapper;

    public PlayerServiceImpl(PlayerRepository playerRepository,
                             TeamRepository teamRepository,
                             PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.playerMapper = playerMapper;
    }

    @Override
    @Transactional
    public PlayerDTO listPlayerOnTransfer(String userEmail, ListPlayerOnTransferRequest request) {
        var player = this.findPlayer(request.getPlayerId());
        var userTeam = teamRepository.findByUserEmail(userEmail);

        Optional.of(userTeam).map(Team::getId).filter(id -> id == player.getTeam().getId())
                        .orElseThrow(() -> new IllegalStateException("Player is not in your list"));

        player.setStatus(PlayerStatus.ON_TRANSFER_LIST.name());
        player.setMarketValue(request.getAskingPrice());
        return playerMapper.mapToPlayerDTO(player);
    }

    @Override
    public List<PlayerDTO> viewListedPlayers(String userEmail) {
        return playerRepository
                .listPlayersWithStatusAndUserEmailNotEqualsTo(userEmail, PlayerStatus.ON_TRANSFER_LIST.name())
                .stream().map(playerMapper::mapToPlayerDTO)
                .toList();
    }

    @Override
    @Transactional
    public PlayerDTO purchasePlayer(String userEmail, int playerId) {
        var player = this.findPlayer(playerId);

        Optional.ofNullable(player.getStatus()).map(PlayerStatus::valueOf)
                .filter(playerStatus -> playerStatus == PlayerStatus.ON_TRANSFER_LIST)
                .orElseThrow(() -> new IllegalStateException("Player is not on transfer list: " + playerId));

        var userTeam = teamRepository.findByUserEmail(userEmail);
        var playerTeam = teamRepository.findById(player.getTeam().getId()).orElseThrow();

        Optional.of(userTeam)
                .map(Team::getBudget)
                .filter(budget -> budget.compareTo(player.getMarketValue()) >= 0)
                .orElseThrow(() -> new IllegalStateException("Team budget is not enough"));

        Optional.of(userTeam).map(Team::getId).filter(id -> id != playerTeam.getId())
                .orElseThrow(() -> new IllegalStateException("You can't purchase your player"));

        player.setStatus(null);
        player.setTeam(userTeam);

        userTeam.setBudget(userTeam.getBudget().subtract(player.getMarketValue()));
        playerTeam.setBudget(playerTeam.getBudget().add(player.getMarketValue()));

        return playerMapper.mapToPlayerDTO(player);
    }

    private Player findPlayer(int playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found: " + playerId));
    }
}
