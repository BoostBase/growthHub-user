package com.growthhub.user.dto.request;

public record OnboardingCompleteRequest(
        Long userId
) {
    public static OnboardingCompleteRequest from(Long userId) {
        return new OnboardingCompleteRequest(userId);
    }
}
