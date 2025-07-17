package com.cabbookingsystem.entity;
import com.cabbookingsystem.enums.Role;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    private String name;
    private String phone;
    private String licenseNumber;
    private String vehicleDetails;
    private String passwordHash;

    private boolean available;

    @Enumerated(EnumType.STRING)
    private Role role;
}


