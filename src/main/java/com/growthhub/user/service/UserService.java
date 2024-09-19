package com.growthhub.user.service;

import com.growthhub.user.dto.request.OnboardingInfoRequest;
import com.growthhub.user.repository.OnboardingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final OnboardingRepository onboardingRepository;

    @Transactional
    public void onboardingComplete(Long userId, OnboardingInfoRequest onboardingInfoRequest) {
        onboardingRepository.save(onboardingInfoRequest.toOnboarding(userId));
    }
}
