package com.growthhub.user.dto.request;

import com.growthhub.user.domain.Career;
import java.time.LocalDate;

public record MentorOnboardingCareerRequest(
        String association,
        LocalDate enterDate,
        LocalDate retireDate,
        String part
) {
    public Career toCareer(Long userId) {
        return Career.builder()
                .userId(userId)
                .isVerified(false)
                .association(association)
                .enterDate(enterDate)
                .retireDate(retireDate)
                .part(part)
                .build();
    }
}
