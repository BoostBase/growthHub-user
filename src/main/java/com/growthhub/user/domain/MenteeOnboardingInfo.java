package com.growthhub.user.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "onboarding_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MenteeOnboardingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "onboarding", cascade = CascadeType.ALL, orphanRemoval = true)
    List<MenteeOnboardingInfoDetail> details;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Builder
    public MenteeOnboardingInfo(Long userId) {
        this.userId = userId;
    }

    public void setDetails(List<MenteeOnboardingInfoDetail> details) {
        this.details = details;
        for (MenteeOnboardingInfoDetail detail : details) {
            detail.enrollOnboarding(this); // OnboardingDetail의 onboarding 필드도 설정
        }
    }
}