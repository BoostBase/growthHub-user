package com.growthhub.user.dto.request;

import com.growthhub.user.domain.OnboardingInfo;
import java.util.List;
import lombok.Builder;

@Builder
public record OnboardingInfoKafkaRequest(
        Long userId,
        List<OnboardingInfoDetailRequest> onboardingInfoDetailRequestList
) {
    public static OnboardingInfoKafkaRequest from(OnboardingInfo onboardingInfo) {
        return OnboardingInfoKafkaRequest.builder()
                .userId(onboardingInfo.getUserId())
                .onboardingInfoDetailRequestList(
                        onboardingInfo.getDetails().stream()
                                .map(OnboardingInfoDetailRequest::from)
                                .toList()
                ).build();
    }
}
