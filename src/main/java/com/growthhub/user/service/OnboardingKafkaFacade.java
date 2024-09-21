package com.growthhub.user.service;

import com.growthhub.user.dto.request.OnboardingCompleteRequest;
import com.growthhub.user.dto.request.OnboardingInfoRequest;
import com.growthhub.user.repository.OnboardingOutboxRepository;
import com.growthhub.user.repository.OnboardingRepository;
import com.growthhub.user.util.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class OnboardingKafkaFacade {

    private final KafkaProducer kafkaProducer;
    private final OnboardingRepository onboardingRepository;
    private final OnboardingOutboxRepository onboardingOutboxRepository;

    public void sendOnboardingComplete(Long outboxId) {
        onboardingOutboxRepository.findById(outboxId).ifPresent(onboardingOutbox -> {
            onboardingRepository.findById(onboardingOutbox.getOnboardingId()).ifPresent(onboarding -> {
                //auth-service로 onboarding-ok 메시지 전송(isOnboarded = true 변경)
                kafkaProducer.send("onboarding-ok", OnboardingCompleteRequest.from(onboarding.getUserId()));
                //recommend-service로 onboarding-info 메시지 전송(onboarding 정보 전송)
                kafkaProducer.send("onboarding-info", OnboardingInfoRequest.from(onboarding));

                //onboarding 삭제
//                onboardingRepository.delete(onboarding);
            });

            //outbox 삭제
            onboardingOutboxRepository.delete(onboardingOutbox);
        });
    }
}
