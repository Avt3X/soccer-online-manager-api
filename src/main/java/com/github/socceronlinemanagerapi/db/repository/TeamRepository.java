package com.github.socceronlinemanagerapi.db.repository;

import com.github.socceronlinemanagerapi.db.entity.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Integer> {
}
