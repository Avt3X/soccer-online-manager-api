package com.github.socceronlinemanagerapi.api.controller;

import com.github.socceronlinemanagerapi.api.request.ListPlayerOnTransferRequest;
import com.github.socceronlinemanagerapi.pojos.PlayerDTO;
import com.github.socceronlinemanagerapi.servicies.PlayerService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/listedontransfer")
    public List<PlayerDTO> viewListedPlayers(@AuthenticationPrincipal UserDetails userDetails) {
        return playerService.viewListedPlayers(userDetails.getUsername());
    }

    @PostMapping("/listontransfer")
    public PlayerDTO listPlayerOnTransfer(@AuthenticationPrincipal UserDetails userDetails,
                                          @Valid @RequestBody ListPlayerOnTransferRequest request) {
        return playerService.listPlayerOnTransfer(userDetails.getUsername(), request);
    }

    @PostMapping("/purchase")
    public PlayerDTO purchasePlayer(@AuthenticationPrincipal UserDetails userDetails,
                                    @RequestParam int playerId) {
        return playerService.purchasePlayer(userDetails.getUsername(), playerId);
    }

}
