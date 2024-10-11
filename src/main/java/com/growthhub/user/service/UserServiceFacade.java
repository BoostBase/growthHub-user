package com.growthhub.user.service;

import com.growthhub.user.domain.type.Role;
import com.growthhub.user.dto.request.MenteeOnboardingInfoRequest;
import com.growthhub.user.dto.request.MentorOnboardingRequest;
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

    public void mentorOnboardingComplete(Long userId, MentorOnboardingRequest request) {
        Long outboxId = userService.mentorOnboardingComplete(userId, request);
        onboardingKafkaFacade.sendMentorOnboardingComplete(outboxId);
    }
}
