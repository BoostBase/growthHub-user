package com.growthhub.user.dto.request;

import com.growthhub.user.domain.OnboardingInfo;
import com.growthhub.user.domain.type.CompanySize;
import com.growthhub.user.domain.type.MentorType;
import com.growthhub.user.domain.type.Purpose;
import com.growthhub.user.domain.type.Role;
import lombok.Builder;

@Builder
public record OnboardingInfoRequest(
        Role role,
        CompanySize companySize,
        MentorType mentorType,
        Purpose purpose,
        String onboardingDetail
) {
    public OnboardingInfo toOnboarding(Long userId) {
        return OnboardingInfo.builder()
                .userId(userId)
                .companySize(companySize)
                .mentorType(mentorType)
                .role(role)
                .purpose(purpose)
                .onboardingDetail(onboardingDetail)
                .build();
    }

    public static OnboardingInfoRequest from(OnboardingInfo onboardingInfo) {
        return OnboardingInfoRequest.builder()
                .companySize(onboardingInfo.getCompanySize())
                .mentorType(onboardingInfo.getMentorType())
                .purpose(onboardingInfo.getPurpose())
                .onboardingDetail(onboardingInfo.getOnboardingDetail())
                .build();
    }
}
