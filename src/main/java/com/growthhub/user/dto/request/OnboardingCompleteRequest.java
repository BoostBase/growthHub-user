package com.growthhub.user.dto.request;

import com.growthhub.user.domain.type.Role;

public record OnboardingCompleteRequest(
        Long userId,
        Role role
) {
    public static OnboardingCompleteRequest from(Long userId, Role role) {
        return new OnboardingCompleteRequest(userId, role);
    }
}
