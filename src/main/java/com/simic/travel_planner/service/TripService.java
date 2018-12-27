package com.simic.travel_planner.service;

import com.simic.travel_planner.dto.TripDto;

import java.util.List;

public interface TripService {

    List<TripDto> getAllTrips(int userId);

    List<TripDto> getAllTripsFiltered(int userId, String destination, String comment);

    TripDto getTripById(int userId, int tripId);

    TripDto createTrip(int userId, TripDto tripDto);

    TripDto updateTrip(int userId, int tripId, TripDto tripDto);

    boolean deleteTrip(int userId, int tripId);
}
