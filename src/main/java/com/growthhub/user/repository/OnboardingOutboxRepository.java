package com.growthhub.user.repository;

import com.growthhub.user.domain.OnboardingOutbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnboardingOutboxRepository extends JpaRepository<OnboardingOutbox, Long> {
}
