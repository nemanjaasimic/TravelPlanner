package com.simic.travel_planner.service.impl;

import com.simic.travel_planner.dto.TripDto;
import com.simic.travel_planner.exception.DeletionException;
import com.simic.travel_planner.exception.TripException;
import com.simic.travel_planner.exception.TripNotFoundException;
import com.simic.travel_planner.exception.UserNotFoundException;
import com.simic.travel_planner.model.Trip;
import com.simic.travel_planner.model.User;
import com.simic.travel_planner.repository.TripRepository;
import com.simic.travel_planner.repository.UserRepository;
import com.simic.travel_planner.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    private final UserRepository userRepository;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository, UserRepository userRepository) {
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TripDto> getAllTrips(int userId) {
        if(userRepository.getRole(userId).equals("ROLE_ADMIN")) {
            // if user is ADMIN then he should be able to get all trips created
            return tripRepository
                    .findAll()
                    .stream()
                    .map(TripDto::new)
                    .collect(Collectors.toList());
        } else if(userRepository.getRole(userId).equals("ROLE_USER")) {
            // if user is regular user, he should only get his trips
            return tripRepository
                    .getAllByUserId(userId)
                    .orElseThrow(() -> new TripNotFoundException("Could not get Your trips."))
                    .stream()
                    .map(TripDto::new)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<TripDto> getAllTripsFiltered(int userId, String destination, String comment) {
        final List<TripDto> trips = new ArrayList<>();
        for (TripDto trip : getAllTrips(userId)) {
            if(trip.getDestination().toLowerCase().contains(destination.toLowerCase())) {
                if(comment != null && !comment.isEmpty()) {
                    if(trip.getComment().toLowerCase().contains(comment.toLowerCase())) {
                        trips.add(trip);
                    }
                }
                else {
                    trips.add(trip);
                }
            }
        }

        return trips;
    }

    @Override
    public TripDto getTripById(int userId, int tripId) {
        if(userRepository.getRole(userId).equals("ROLE_ADMIN")) {
            return new TripDto(tripRepository
                    .findById(tripId)
                    .orElseThrow(() -> new TripNotFoundException("Could not find trip with given id.")));
        } else if(userRepository.getRole(userId).equals("ROLE_USER")) {
            // if user is regular user, he should only get his trips
            final Trip trip = tripRepository
                    .findById(tripId)
                    .orElseThrow(() -> new TripNotFoundException("Could not find trip with given id."));
            if(trip.getUser().getUserId() != userId) {
                throw new TripNotFoundException("Could not find trip with given id.");
            } else {
                return new TripDto(trip);
            }
        }

        throw new TripNotFoundException("Could not find trip with given id.");
    }

    @Override
    public TripDto createTrip(int userId, TripDto tripDto) {
        if(!userRepository.getRole(userId).equals("ROLE_ADMIN") && (tripDto.getUserId() != userId)) {
            throw new TripException("Could not create trip for other user!");
        }

        try {
            final User user = userRepository
                    .findById(tripDto.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("Could not find user with given id."));

            Trip trip = new Trip(tripDto);
            trip.setUser(user);

            trip = tripRepository.save(trip);
            if(trip != null)
                return new TripDto(trip);
        } catch (Exception e) {
            throw new TripException("Error while creating trip.");
        }

        return null;
    }

    @Override
    public TripDto updateTrip(int userId, int tripId, TripDto tripDto) {
        if(!userRepository.getRole(userId).equals("ROLE_ADMIN") && tripDto.getUserId() != userId) {
            throw new TripException("Could not update trip for other user!");
        }

        try {
            final User user = userRepository
                    .findById(tripDto.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("Could not find user with given id."));
            if(user.getUserId() != tripDto.getUserId()) {
                throw new TripException("You can't update Trip which isn't Your.");
            }

            Trip trip = tripRepository
                    .findById(tripId)
                    .orElseThrow(() -> new TripNotFoundException("Could not find trip with given id."));

            trip.setStartDate(tripDto.getStartDate());
            trip.setEndDate(tripDto.getEndDate());
            if(trip.getComment() != null) {
                trip.setComment(tripDto.getComment());
            }

            trip = tripRepository.save(trip);
            if(trip != null) {
                return new TripDto(trip);
            }
        } catch (Exception e) {
            throw new TripException("Error while updating trip.");
        }

        return null;
    }

    @Override
    public boolean deleteTrip(int userId, int tripId) {
        try {
            final Trip trip = tripRepository
                    .findById(tripId)
                    .orElseThrow(() -> new TripNotFoundException("Could not find trip with given id."));

            final User user = userRepository
                    .findById(trip.getUser().getUserId())
                    .orElseThrow(() -> new UserNotFoundException("Could not find user with given id."));
            if(!user.getRole().getName().equals("ROLE_ADMIN") || trip.getUser().getUserId() != user.getUserId()) {
                throw new DeletionException("Could not delete trip which isn't Your!");
            }

            tripRepository.delete(trip);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
