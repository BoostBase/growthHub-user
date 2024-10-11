package com.growthhub.user.dto.request;

import com.growthhub.user.domain.MenteeOnboardingDetail;
import com.growthhub.user.domain.MenteeOnboarding;
import com.growthhub.user.domain.type.Role;
import java.util.List;

public record MenteeOnboardingInfoRequest(
        Role role,
        List<MenteeOnboardingInfoDetailRequest> menteeOnboardingInfoDetailRequestList
) {
    public MenteeOnboarding toOnboarding(Long userId) {
        // MenteeOnboarding 객체 생성
        MenteeOnboarding onboarding = MenteeOnboarding.builder()
                .userId(userId)
                .build();

        // OnboardingDetail 리스트 생성
        List<MenteeOnboardingDetail> details = menteeOnboardingInfoDetailRequestList.stream()
                .map(detailRequest -> MenteeOnboardingDetail.builder()
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
