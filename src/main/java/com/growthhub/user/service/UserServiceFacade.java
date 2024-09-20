package com.growthhub.user.service;

import com.growthhub.user.dto.request.OnboardingInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceFacade {

    private final UserService userService;
    private final OnboardingKafkaFacade onboardingKafkaFacade;

    public void onboardingComplete(Long userId, OnboardingInfoRequest onboardingInfoRequest) {
        Long outboxId = userService.onboardingComplete(userId, onboardingInfoRequest);
        onboardingKafkaFacade.sendOnboardingComplete(outboxId);
    }
}
