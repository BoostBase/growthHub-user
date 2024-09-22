package com.growthhub.user.repository;

import com.growthhub.user.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT r.mentorId, AVG(r.score) FROM Rating r WHERE r.mentorId IN :mentorIds GROUP BY r.mentorId")
    List<Object[]> findAverageRatingForUsers(@Param("mentorIds") List<Long> mentorIds);
}
