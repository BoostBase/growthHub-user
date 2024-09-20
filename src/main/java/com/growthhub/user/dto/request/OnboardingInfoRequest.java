package com.growthhub.user.dto.request;

import com.growthhub.user.domain.Onboarding;
import com.growthhub.user.domain.type.CompanySize;
import com.growthhub.user.domain.type.MentorType;
import com.growthhub.user.domain.type.Purpose;
import jakarta.annotation.Nullable;
import lombok.Builder;

@Builder
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

    public static OnboardingInfoRequest from(Onboarding onboarding) {
        return OnboardingInfoRequest.builder()
                .userId(onboarding.getUserId())
                .companySize(onboarding.getCompanySize())
                .mentorType(onboarding.getMentorType())
                .purpose(onboarding.getPurpose())
                .onboardingDetail(onboarding.getOnboardingDetail())
                .build();
    }
}
