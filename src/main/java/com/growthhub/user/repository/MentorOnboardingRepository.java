package com.growthhub.user.repository;

import com.growthhub.user.domain.MentorOnboarding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorOnboardingRepository extends JpaRepository<MentorOnboarding, Long> {
}
