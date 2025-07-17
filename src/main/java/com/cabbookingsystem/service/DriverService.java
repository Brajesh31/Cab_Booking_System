package com.cabbookingsystem.service;

import com.cabbookingsystem.dto.DriverLoginRequest;
import com.cabbookingsystem.dto.DriverRegisterRequest;
import com.cabbookingsystem.entity.Driver;

import java.util.List;

public interface DriverService {
    String register(DriverRegisterRequest request);
    String login(DriverLoginRequest  request);
    List<Driver> getAvailableDrivers();
    void updateStatus(Long driverId, boolean available);
}