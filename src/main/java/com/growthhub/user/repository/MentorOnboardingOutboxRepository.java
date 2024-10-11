package com.growthhub.user.repository;

import com.growthhub.user.domain.MentorOnboardingOutbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorOnboardingOutboxRepository extends JpaRepository<MentorOnboardingOutbox, Long> {
}
