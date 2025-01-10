package com.github.socceronlinemanagerapi.db.repository;

import com.github.socceronlinemanagerapi.db.entity.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

    @Query("SELECT p FROM Player p" +
            "         LEFT JOIN Team t ON p.team.id = t.id" +
            "         LEFT JOIN User u ON t.id = u.team.id WHERE u.email != :userEmail AND p.status = :status")
    List<Player> listPlayersWithStatusAndUserEmailNotEqualsTo(@Param("userEmail") String userEmail, @Param("status") String status);
}
