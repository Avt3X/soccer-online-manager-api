package com.github.socceronlinemanagerapi.db.repository;

import com.github.socceronlinemanagerapi.db.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);
}
