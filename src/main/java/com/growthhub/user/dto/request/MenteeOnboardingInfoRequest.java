package com.growthhub.user.dto.request;

import com.growthhub.user.domain.MenteeOnboardingInfoDetail;
import com.growthhub.user.domain.MenteeOnboardingInfo;
import com.growthhub.user.domain.type.Role;
import java.util.List;

public record MenteeOnboardingInfoRequest(
        Role role,
        List<MenteeOnboardingInfoDetailRequest> menteeOnboardingInfoDetailRequestList
) {
    public MenteeOnboardingInfo toOnboarding(Long userId) {
        // MenteeOnboarding 객체 생성
        MenteeOnboardingInfo onboarding = MenteeOnboardingInfo.builder()
                .userId(userId)
                .build();

        // OnboardingDetail 리스트 생성
        List<MenteeOnboardingInfoDetail> details = menteeOnboardingInfoDetailRequestList.stream()
                .map(detailRequest -> MenteeOnboardingInfoDetail.builder()
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
