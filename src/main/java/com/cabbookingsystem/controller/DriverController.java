package com.cabbookingsystem.controller;

import com.cabbookingsystem.dto.DriverLoginRequest;
import com.cabbookingsystem.dto.DriverRegisterRequest;
import com.cabbookingsystem.entity.Driver;
import com.cabbookingsystem.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody DriverRegisterRequest request) {
        return ResponseEntity.ok(driverService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody DriverLoginRequest request) {
        return ResponseEntity.ok(driverService.login(request));
    }

    @GetMapping("/available")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Driver>> getAvailableDrivers() {
        return ResponseEntity.ok(driverService.getAvailableDrivers());
    }

    @PutMapping("/status/{id}")
    @PreAuthorize("hasRole('DRIVER') or hasRole('ADMIN')")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam boolean available) {
        driverService.updateStatus(id, available);
        return ResponseEntity.ok("Driver status updated");
    }
}
