package com.growthhub.user.service;

import com.growthhub.user.domain.type.Role;
import com.growthhub.user.dto.request.OnboardingCompleteRequest;
import com.growthhub.user.repository.MenteeOnboardingOutboxRepository;
import com.growthhub.user.repository.MenteeOnboardingInfoRepository;
import com.growthhub.user.util.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class OnboardingKafkaFacade {

    private final KafkaProducer kafkaProducer;
    private final MenteeOnboardingInfoRepository menteeOnboardingInfoRepository;
    private final MenteeOnboardingOutboxRepository menteeOnboardingOutboxRepository;

    public void sendOnboardingComplete(Long outboxId, Role role) {
        menteeOnboardingOutboxRepository.findById(outboxId).ifPresent(onboardingOutbox -> {
            menteeOnboardingInfoRepository.findById(onboardingOutbox.getOnboardingId()).ifPresent(onboarding -> {
                //auth-service로 onboarding-ok 메시지 전송(isOnboarded = true 변경)
                kafkaProducer.send("onboarding-ok",
                        OnboardingCompleteRequest.from(onboarding.getUserId(), role));
            });

            //outbox 삭제
            menteeOnboardingOutboxRepository.delete(onboardingOutbox);
        });
    }
}
