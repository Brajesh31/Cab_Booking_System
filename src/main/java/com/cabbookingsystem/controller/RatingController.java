package com.cabbookingsystem.controller;

import com.cabbookingsystem.dto.RatingRequest;
import com.cabbookingsystem.entity.Rating;
import com.cabbookingsystem.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('DRIVER') or hasRole('ADMIN')")
    public ResponseEntity<Rating> submitRating(@RequestBody RatingRequest request) {
        return ResponseEntity.ok(ratingService.submitRating(request));
    }

    @GetMapping("/user/{toUserId}")
    @PreAuthorize(" hasRole('DRIVER')")
    public ResponseEntity<List<Rating>> getRatings(@PathVariable Long toUserId) {
        return ResponseEntity.ok(ratingService.getRatings(toUserId));
    }
}
