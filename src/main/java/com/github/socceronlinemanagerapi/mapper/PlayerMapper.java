package com.github.socceronlinemanagerapi.mapper;

import com.github.socceronlinemanagerapi.db.entity.Player;
import com.github.socceronlinemanagerapi.pojos.PlayerDTO;
import com.github.socceronlinemanagerapi.pojos.PlayerPosition;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public PlayerDTO mapToPlayerDTO(Player player) {
        return PlayerDTO.builder()
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .age(player.getAge())
                .position(PlayerPosition.valueOf(player.getPosition()))
                .marketValue(player.getMarketValue())
                .build();
    }
}
