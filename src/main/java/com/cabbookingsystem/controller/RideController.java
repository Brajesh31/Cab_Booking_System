package com.cabbookingsystem.controller;

import com.cabbookingsystem.dto.RideBookingRequest;
import com.cabbookingsystem.entity.Ride;
import com.cabbookingsystem.entity.User;
import com.cabbookingsystem.repository.UserRepository;
import com.cabbookingsystem.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;
    private final UserRepository userRepository;

    @PostMapping("/book")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Ride> bookRide(@RequestBody RideBookingRequest request) {
        return ResponseEntity.ok(rideService.bookRide(request));
    }

    @PutMapping("/status/{id}")
    @PreAuthorize("hasRole('DRIVER') or hasRole('ADMIN')")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam String status) {
        rideService.updateStatus(id, status);
        return ResponseEntity.ok("Ride status updated");
    }

     @GetMapping("/user/rides")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Ride>> getUserRides() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(rideService.getUserRides(user.getUserId()));
    }
}
