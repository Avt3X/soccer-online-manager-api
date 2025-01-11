package com.github.socceronlinemanagerapi.servicies;

import com.github.socceronlinemanagerapi.db.entity.Player;
import com.github.socceronlinemanagerapi.db.entity.Team;
import com.github.socceronlinemanagerapi.db.repository.PlayerRepository;
import com.github.socceronlinemanagerapi.db.repository.TeamRepository;
import com.github.socceronlinemanagerapi.mapper.PlayerMapper;
import com.github.socceronlinemanagerapi.pojos.PlayerDTO;
import com.github.socceronlinemanagerapi.pojos.PlayerStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PlayerServiceImplTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private PlayerMapper playerMapper;

    @InjectMocks
    private PlayerServiceImpl playerService;

    private Player player;
    private Team userTeam;
    private Team playerTeam;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mocking Teams
        userTeam = new Team();
        userTeam.setId(1);
        userTeam.setBudget(BigDecimal.valueOf(1000000));

        playerTeam = new Team();
        playerTeam.setId(2);
        playerTeam.setBudget(BigDecimal.valueOf(800000));

        // Mocking Player
        player = new Player();
        player.setId(1);
        player.setMarketValue(BigDecimal.valueOf(500000));
        player.setStatus(PlayerStatus.ON_TRANSFER_LIST.name());
        player.setTeam(playerTeam);
    }

    @Test
    void testPurchasePlayerSuccess() {
        // Arrange: Mock team repository behavior
        when(teamRepository.findByUserEmail("user@example.com")).thenReturn(userTeam);
        when(teamRepository.findById(player.getTeam().getId())).thenReturn(Optional.of(playerTeam));
        when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));
        when(playerMapper.mapToPlayerDTO(any(Player.class))).thenReturn(PlayerDTO.builder().build());

        // Act: Call the purchasePlayer method
        PlayerDTO result = playerService.purchasePlayer("user@example.com", 1);

        // Assert: Verify the correct method calls and the expected result
        verify(teamRepository).findByUserEmail("user@example.com");
        verify(teamRepository).findById(playerTeam.getId());
        verify(playerMapper).mapToPlayerDTO(any(Player.class));

        assertNotNull(result);
        assertEquals(userTeam, player.getTeam());
        assertEquals(userTeam.getBudget(), BigDecimal.valueOf(500000));
        assertEquals(playerTeam.getBudget(), BigDecimal.valueOf(1300000));
    }

    @Test
    void testPlayerNotOnTransferList() {
        // Arrange: Set player status to something other than ON_TRANSFER_LIST
        player.setStatus(null);
        when(teamRepository.findByUserEmail("user@example.com")).thenReturn(userTeam);
        when(teamRepository.findById(player.getTeam().getId())).thenReturn(Optional.of(playerTeam));
        when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));

        // Act and Assert: Check if IllegalStateException is thrown
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            playerService.purchasePlayer("user@example.com", 1);
        });
        assertEquals("Player is not on transfer list: 1", exception.getMessage());
    }

    @Test
    void testTeamBudgetNotEnough() {
        // Arrange: Set user team budget to less than player market value
        userTeam.setBudget(BigDecimal.valueOf(400000));  // Insufficient budget
        when(teamRepository.findByUserEmail("user@example.com")).thenReturn(userTeam);
        when(teamRepository.findById(player.getTeam().getId())).thenReturn(Optional.of(playerTeam));
        when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));

        // Act and Assert: Check if IllegalStateException is thrown
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            playerService.purchasePlayer("user@example.com", 1);
        });
        assertEquals("Team budget is not enough", exception.getMessage());
    }

    @Test
    void testCannotPurchaseOwnPlayer() {
        // Arrange: Set user team and player team to be the same
        userTeam.setId(2); // Same as player team
        when(teamRepository.findByUserEmail("user@example.com")).thenReturn(userTeam);
        when(teamRepository.findById(player.getTeam().getId())).thenReturn(Optional.of(playerTeam));
        when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));

        // Act and Assert: Check if IllegalStateException is thrown
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            playerService.purchasePlayer("user@example.com", 1);
        });
        assertEquals("You can't purchase your player", exception.getMessage());
    }

    @Test
    void testPlayerNotFound() {
        // Arrange: Return empty when trying to find the player
        when(teamRepository.findByUserEmail("user@example.com")).thenReturn(userTeam);
        when(teamRepository.findById(player.getTeam().getId())).thenReturn(Optional.of(playerTeam));
        when(playerRepository.findById(player.getId())).thenReturn(Optional.empty());
        when(playerMapper.mapToPlayerDTO(any(Player.class))).thenReturn(PlayerDTO.builder().build());

        // Act and Assert: Check if IllegalStateException is thrown when player is not found
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            playerService.purchasePlayer("user@example.com", 1);
        });
        assertEquals("Player not found: " + player.getId(), exception.getMessage());
    }

}