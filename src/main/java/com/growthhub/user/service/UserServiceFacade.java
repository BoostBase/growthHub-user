package com.growthhub.user.service;

import com.growthhub.user.domain.type.Role;
import com.growthhub.user.dto.request.MenteeOnboardingInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceFacade {

    private final UserService userService;
    private final OnboardingKafkaFacade onboardingKafkaFacade;

    public void menteeOnboardingComplete(Long userId, MenteeOnboardingInfoRequest menteeOnboardingInfoRequest) {
        Long outboxId = userService.menteeOnboardingComplete(userId, menteeOnboardingInfoRequest);
        onboardingKafkaFacade.sendOnboardingComplete(outboxId, Role.MENTEE);
    }
}
