package com.growthhub.user.dto.request;

import com.growthhub.user.domain.MentorOnboarding;
import lombok.Builder;

@Builder
public record MentorOnboardingKafkaRequest(
        Long userId,
        String association,
        String part,
        Long careerYear
) {
    public static MentorOnboardingKafkaRequest from(MentorOnboarding mentorOnboarding) {
        return MentorOnboardingKafkaRequest.builder()
                .userId(mentorOnboarding.getUserId())
                .association(mentorOnboarding.getAssociation())
                .part(mentorOnboarding.getPart())
                .careerYear(mentorOnboarding.getCareerYear())
                .build();
    }
}
