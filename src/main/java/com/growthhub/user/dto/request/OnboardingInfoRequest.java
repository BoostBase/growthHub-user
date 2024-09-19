package com.growthhub.user.dto.request;

import com.growthhub.user.domain.Onboarding;
import com.growthhub.user.domain.type.CompanySize;
import com.growthhub.user.domain.type.MentorType;
import com.growthhub.user.domain.type.Purpose;
import jakarta.annotation.Nullable;

public record OnboardingInfoRequest(
        @Nullable Long userId,
        CompanySize companySize,
        MentorType mentorType,
        Purpose purpose,
        String onboardingDetail
) {
    public Onboarding toOnboarding(Long userId) {
        return Onboarding.builder()
                .userId(userId)
                .companySize(companySize)
                .mentorType(mentorType)
                .purpose(purpose)
                .onboardingDetail(onboardingDetail)
                .build();
    }
}
