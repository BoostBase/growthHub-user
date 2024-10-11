package com.growthhub.user.dto.request;

import com.growthhub.user.domain.MenteeOnboardingInfoDetail;
import com.growthhub.user.domain.type.OnboardingDetailType;

public record MenteeOnboardingInfoDetailRequest(
        OnboardingDetailType type,
        String value
) {
    public static MenteeOnboardingInfoDetailRequest from(MenteeOnboardingInfoDetail menteeOnboardingInfoDetail) {
        return new MenteeOnboardingInfoDetailRequest(
                menteeOnboardingInfoDetail.getType(),
                menteeOnboardingInfoDetail.getValue()
        );
    }
}
