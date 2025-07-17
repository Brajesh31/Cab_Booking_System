package com.cabbookingsystem.service.impl;

import com.cabbookingsystem.dto.RatingRequest;
import com.cabbookingsystem.entity.Rating;
import com.cabbookingsystem.entity.Ride;
import com.cabbookingsystem.repository.RatingRepository;
import com.cabbookingsystem.repository.RideRepository;
import com.cabbookingsystem.service.RatingService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final RideRepository rideRepository;

    @Override
    public Rating submitRating(RatingRequest request) {
        Ride ride = rideRepository.findById(request.getRideId())
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        // Get authenticated user's email
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long fromUserId = ride.getUser().getUserId();
        // Optionally, check that the authenticated user matches ride.getUser().getEmail()
        if (!ride.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized rating attempt");
        }
        Long toUserId = ride.getDriver().getDriverId();

        Rating rating = Rating.builder()
                .ride(ride)
                .fromUserId(fromUserId)
                .toUserId(toUserId)
                .score(request.getScore())
                .comments(request.getComments())
                .build();

        return ratingRepository.save(rating);
    }
    
    @Override
    public List<Rating> getRatings(Long toUserId) {
        return ratingRepository.findByToUserId(toUserId);
    }
}
