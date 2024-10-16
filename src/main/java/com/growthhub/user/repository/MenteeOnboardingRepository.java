package com.growthhub.user.repository;

import com.growthhub.user.domain.MenteeOnboarding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenteeOnboardingRepository extends JpaRepository<MenteeOnboarding, Long> {
}