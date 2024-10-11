package com.growthhub.user.dto.request;

import com.growthhub.user.domain.MenteeOnboardingDetail;
import com.growthhub.user.domain.type.OnboardingDetailType;

public record MenteeOnboardingInfoDetailRequest(
        OnboardingDetailType type,
        String value
) {
    public static MenteeOnboardingInfoDetailRequest from(MenteeOnboardingDetail menteeOnboardingDetail) {
        return new MenteeOnboardingInfoDetailRequest(
                menteeOnboardingDetail.getType(),
                menteeOnboardingDetail.getValue()
        );
    }
}
