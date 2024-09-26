package com.growthhub.user.dto.request;

import com.growthhub.user.domain.OnboardingInfoDetail;
import com.growthhub.user.domain.OnboardingInfo;
import com.growthhub.user.domain.type.Role;
import java.util.List;

public record OnboardingInfoRequest(
        Role role,
        List<OnboardingInfoDetailRequest> onboardingInfoDetailRequestList
) {
    public OnboardingInfo toOnboarding(Long userId) {
        // Onboarding 객체 생성
        OnboardingInfo onboarding = OnboardingInfo.builder()
                .userId(userId)
                .role(role)
                .build();

        // OnboardingDetail 리스트 생성
        List<OnboardingInfoDetail> details = onboardingInfoDetailRequestList.stream()
                .map(detailRequest -> OnboardingInfoDetail.builder()
                        .onboarding(onboarding) // 현재 Onboarding 객체를 설정
                        .type(detailRequest.type())
                        .value(detailRequest.value())
                        .build())
                .toList();

        // Onboarding 객체의 details 필드에 설정
        onboarding.setDetails(details);

        return onboarding;
    }
}
