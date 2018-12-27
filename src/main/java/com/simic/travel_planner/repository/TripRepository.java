package com.simic.travel_planner.repository;

import com.simic.travel_planner.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

    @Query(value = "SELECT * FROM Trip t where t.user_id = ?1", nativeQuery = true)
    Optional<List<Trip>> getAllByUserId(int userId);
}
