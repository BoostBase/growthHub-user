package com.growthhub.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "mentor_onboarding_outbox")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MentorOnboardingOutbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "onboarding_id", nullable = false)
    private Long onboardingId;

    @Builder
    public MentorOnboardingOutbox(Long onboardingId) {
        this.onboardingId = onboardingId;
    }

    public static MentorOnboardingOutbox from(MentorOnboarding mentorOnboarding) {
        return MentorOnboardingOutbox.builder()
            .onboardingId(mentorOnboarding.getId())
            .build();
    }
}
