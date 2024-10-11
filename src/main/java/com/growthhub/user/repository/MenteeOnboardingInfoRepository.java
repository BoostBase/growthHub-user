package com.growthhub.user.repository;

import com.growthhub.user.domain.MenteeOnboardingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenteeOnboardingInfoRepository extends JpaRepository<MenteeOnboardingInfo, Long> {
}
