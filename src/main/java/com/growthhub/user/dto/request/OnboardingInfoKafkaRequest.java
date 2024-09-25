package com.growthhub.user.dto.request;

import com.growthhub.user.domain.OnboardingInfo;
import com.growthhub.user.domain.type.CompanySize;
import com.growthhub.user.domain.type.MentorType;
import com.growthhub.user.domain.type.Purpose;
import lombok.Builder;

@Builder
public record OnboardingInfoKafkaRequest(
        Long userId,
        CompanySize companySize,
        MentorType mentorType,
        Purpose purpose,
        String onboardingDetail
) {
    public static OnboardingInfoKafkaRequest from(OnboardingInfo onboardingInfo) {
        return OnboardingInfoKafkaRequest.builder()
                .userId(onboardingInfo.getUserId())
                .companySize(onboardingInfo.getCompanySize())
                .mentorType(onboardingInfo.getMentorType())
                .purpose(onboardingInfo.getPurpose())
                .onboardingDetail(onboardingInfo.getOnboardingDetail())
                .build();
    }
}
