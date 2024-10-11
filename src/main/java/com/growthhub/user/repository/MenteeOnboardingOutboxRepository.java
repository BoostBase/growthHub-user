package com.growthhub.user.repository;

import com.growthhub.user.domain.MenteeOnboardingOutbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenteeOnboardingOutboxRepository extends JpaRepository<MenteeOnboardingOutbox, Long> {
}
