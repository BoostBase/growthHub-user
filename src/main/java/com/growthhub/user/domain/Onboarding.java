package com.growthhub.user.domain;

import com.growthhub.user.domain.type.CompanySize;
import com.growthhub.user.domain.type.MentorType;
import com.growthhub.user.domain.type.Purpose;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "onboarding")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Onboarding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    private CompanySize companySize;

    @Enumerated(EnumType.STRING)
    private MentorType mentorType;

    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Column(name = "onboarding_detail")
    private String onboardingDetail;

    @Builder
    public Onboarding(Long userId, CompanySize companySize, MentorType mentorType, Purpose purpose,
                      String onboardingDetail) {
        this.userId = userId;
        this.companySize = companySize;
        this.mentorType = mentorType;
        this.purpose = purpose;
        this.onboardingDetail = onboardingDetail;
    }
}
