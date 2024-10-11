package com.growthhub.user.dto.request;

import com.growthhub.user.domain.MentorOnboarding;
import java.util.List;

public record MentorOnboardingRequest(
        String association,
        String part,
        String detailPart,
        Long careerYear,
        List<MentorOnboardingCareerRequest> careers
) {
    public MentorOnboarding toMentorOnboarding(Long userId) {
        return  MentorOnboarding.builder()
                .userId(userId)
                .association(association)
                .part(part)
                .detailPart(detailPart)
                .careerYear(careerYear)
                .build();
    }
}
