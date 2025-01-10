package com.github.socceronlinemanagerapi.db.repository;

import com.github.socceronlinemanagerapi.db.entity.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface TeamRepository extends CrudRepository<Team, Integer> {

    @Query("SELECT t FROM Team t INNER JOIN User u ON t.id = u.team.id WHERE u.email = :userEmail")
    Team findByUserEmail(@Param("userEmail") String userEmail);
}
