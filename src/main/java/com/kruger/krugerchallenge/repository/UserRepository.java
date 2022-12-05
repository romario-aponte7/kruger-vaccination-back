package com.kruger.krugerchallenge.repository;

import com.kruger.krugerchallenge.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    User findByUsername(String userName);

    List<User> findAll();

}
