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

    // this method isn't native query because its easier to convert to bool from count() > 0 which returns BigInteger
    @Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = ?1")
    Boolean existsByUsername(String username);

    // this method isn't native query because its easier to convert to bool from count() > 0 which returns BigInteger
    @Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = ?1")
    Boolean existsByEmail(String email);

    @Query(value = "SELECT r.name FROM Role r NATURAL JOIN User u WHERE u.user_id = ?1", nativeQuery = true)
    String getRole(int userId);
}
