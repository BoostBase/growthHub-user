package com.growthhub.user.domain;

import com.growthhub.user.domain.type.OnboardingDetailType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "mentee_onboarding_detail")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MenteeOnboardingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "onboarding_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    MenteeOnboarding onboarding;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    OnboardingDetailType type;

    @Column(name = "value", nullable = false)
    String value;

    @Builder
    public MenteeOnboardingDetail(MenteeOnboarding onboarding, OnboardingDetailType type, String value) {
        this.onboarding = onboarding;
        this.type = type;
        this.value = value;
    }

    public void enrollOnboarding(MenteeOnboarding onboarding) {
        this.onboarding = onboarding;
    }
}
