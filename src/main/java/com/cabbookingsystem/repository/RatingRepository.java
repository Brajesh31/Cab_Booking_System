package com.cabbookingsystem.repository;

import com.cabbookingsystem.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByToUserId(Long toUserId);
}
