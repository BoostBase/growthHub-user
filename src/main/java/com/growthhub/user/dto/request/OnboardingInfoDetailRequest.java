package com.growthhub.user.dto.request;

import com.growthhub.user.domain.OnboardingInfoDetail;
import com.growthhub.user.domain.type.OnboardingDetailType;

public record OnboardingInfoDetailRequest(
        OnboardingDetailType type,
        String value
) {

    public static OnboardingInfoDetailRequest from(OnboardingInfoDetail onboardingInfoDetail) {
        return new OnboardingInfoDetailRequest(
                onboardingInfoDetail.getType(),
                onboardingInfoDetail.getValue()
        );
    }
}
