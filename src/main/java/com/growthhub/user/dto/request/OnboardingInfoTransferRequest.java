package com.growthhub.user.dto.request;

import com.growthhub.user.domain.type.CompanySize;
import com.growthhub.user.domain.type.MentorType;
import com.growthhub.user.domain.type.Purpose;

public record OnboardingInfoTransferRequest(
        Long userId,
        CompanySize companySize,
        MentorType mentorType,
        Purpose purpose,
        String onboardingDetail
) {
}
