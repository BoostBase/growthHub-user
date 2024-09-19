package com.growthhub.user.service;

import com.growthhub.user.dto.request.OnboardingCompleteRequest;
import com.growthhub.user.dto.request.OnboardingInfoRequest;
import com.growthhub.user.util.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceFacade {

    private final UserService userService;
    private final KafkaProducer kafkaProducer;

    public void onboardingComplete(Long userId, OnboardingInfoRequest onboardingInfoRequest) {
        userService.onboardingComplete(userId, onboardingInfoRequest);
        kafkaProducer.send("onboarding-ok", OnboardingCompleteRequest.from(userId));

        //Transactional Outbox Pattern 적용 예정
//        kafkaProducer.send("onboarding-ok", onboardingInfoRequest);
    }
}
