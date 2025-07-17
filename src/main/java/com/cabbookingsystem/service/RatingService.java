package com.cabbookingsystem.service;

import com.cabbookingsystem.dto.RatingRequest;
import com.cabbookingsystem.entity.Rating;

import java.util.List;

public interface RatingService {
    Rating submitRating(RatingRequest request);
    List<Rating> getRatings(Long toUserId);
}
