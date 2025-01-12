package com.github.socceronlinemanagerapi.servicies;

import com.github.socceronlinemanagerapi.api.request.ListPlayerOnTransferRequest;
import com.github.socceronlinemanagerapi.api.request.UpdatePlayerMarketValueRequest;
import com.github.socceronlinemanagerapi.pojos.PlayerDTO;

import java.util.List;

public interface PlayerService {
    PlayerDTO listPlayerOnTransfer(String userEmail, ListPlayerOnTransferRequest request);
    List<PlayerDTO> viewListedPlayers(String userEmail);
    PlayerDTO purchasePlayer(String userEmail, int playerId);
    PlayerDTO updateMarketValue(String userEmail, UpdatePlayerMarketValueRequest request);
}
