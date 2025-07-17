package com.cabbookingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @OneToOne
    private Ride ride;

    private Long fromUserId;
    private Long toUserId;

    private int score;
    private String comments;
}
