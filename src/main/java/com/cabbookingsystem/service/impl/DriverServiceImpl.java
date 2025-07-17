package com.cabbookingsystem.service.impl;

import com.cabbookingsystem.dto.DriverLoginRequest;
import com.cabbookingsystem.dto.DriverRegisterRequest;
import com.cabbookingsystem.entity.Driver;
import com.cabbookingsystem.enums.Role;
import com.cabbookingsystem.repository.DriverRepository;
import com.cabbookingsystem.service.DriverService;
import com.cabbookingsystem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String register(DriverRegisterRequest request) {
        Driver driver = Driver.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .licenseNumber(request.getLicenseNumber())
                .vehicleDetails(request.getVehicleDetails())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(Role.DRIVER)
                .available(true)
                .build();

        driverRepository.save(driver);
        return "Driver registered successfully!";
    }

    @Override
    public String login(DriverLoginRequest request) {
        System.out.println("Incoming login request:");
        System.out.println("Phone: " + request.getPhone());
        System.out.println("Password: " + request.getPassword());

        Driver driver = driverRepository.findByPhone(request.getPhone())
                .orElseThrow(() -> new RuntimeException("Driver not found with phone: " + request.getPhone()));

        System.out.println("Driver found: " + driver.getName());
        System.out.println("Stored hash: " + driver.getPasswordHash());

        if (!passwordEncoder.matches(request.getPassword(), driver.getPasswordHash())) {
            System.out.println("Password mismatch.");
            throw new RuntimeException("Invalid password");
        }

        System.out.println("Password matched. Token will be generated.");
        return jwtUtil.generateToken(driver.getPhone(), driver.getRole());
    }

    @Override
    public List<Driver> getAvailableDrivers() {
        return driverRepository.findByAvailableTrue();
    }

    @Override
    public void updateStatus(Long driverId, boolean available) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        driver.setAvailable(available);
        driverRepository.save(driver);
    }
}