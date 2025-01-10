package com.github.socceronlinemanagerapi.mapper;

import com.github.socceronlinemanagerapi.db.entity.Player;
import com.github.socceronlinemanagerapi.pojos.PlayerDTO;
import com.github.socceronlinemanagerapi.pojos.PlayerPosition;
import com.github.socceronlinemanagerapi.pojos.PlayerStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlayerMapper {

    public PlayerDTO mapToPlayerDTO(Player player) {
        return PlayerDTO.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .age(player.getAge())
                .position(PlayerPosition.valueOf(player.getPosition()))
                .status(Optional.ofNullable(player.getStatus()).map(PlayerStatus::valueOf).orElse(null))
                .marketValue(player.getMarketValue())
                .build();
    }
}
