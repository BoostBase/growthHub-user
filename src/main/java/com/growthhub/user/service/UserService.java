package com.growthhub.user.service;

import com.growthhub.user.domain.Onboarding;
import com.growthhub.user.domain.OnboardingOutbox;
import com.growthhub.user.dto.request.OnboardingInfoRequest;
import com.growthhub.user.repository.OnboardingOutboxRepository;
import com.growthhub.user.repository.OnboardingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final OnboardingRepository onboardingRepository;
    private final OnboardingOutboxRepository onboardingOutboxRepository;

    @Transactional
    public Long onboardingComplete(Long userId, OnboardingInfoRequest onboardingInfoRequest) {
        Onboarding onboarding = onboardingRepository.save(onboardingInfoRequest.toOnboarding(userId));
        OnboardingOutbox outbox = onboardingOutboxRepository.save(OnboardingOutbox.from(onboarding));

        return onboarding.getId();
    }
}
