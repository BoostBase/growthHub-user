package com.growthhub.user.domain;

import com.growthhub.user.domain.type.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class OnboardingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "onboarding", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OnboardingInfoDetail> details;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public OnboardingInfo(Long userId, Role role) {
        this.userId = userId;
        this.role = role;
    }

    public void setDetails(List<OnboardingInfoDetail> details) {
        this.details = details;
        for (OnboardingInfoDetail detail : details) {
            detail.enrollOnboarding(this); // OnboardingDetail의 onboarding 필드도 설정
        }
    }
}
