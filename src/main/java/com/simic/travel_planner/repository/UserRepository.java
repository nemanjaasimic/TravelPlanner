package com.simic.travel_planner.repository;

import com.simic.travel_planner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM User u WHERE u.email = ?1 or  u.username = ?1", nativeQuery = true)
    Optional<User> findByUsernameOrEmail(String usernameOrEmail);

    @Query(value = "SELECT * FROM User u WHERE u.username = ?1", nativeQuery = true)
    User findByUsername(String username);

    @Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = ?1")
    Boolean existsByUsername(String username);

    @Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = ?1")
    Boolean existsByEmail(String email);
}
