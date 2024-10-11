package com.growthhub.user.service;

import com.growthhub.user.domain.type.Role;
import com.growthhub.user.dto.request.MentorOnboardingKafkaRequest;
import com.growthhub.user.dto.request.OnboardingCompleteRequest;
import com.growthhub.user.repository.MenteeOnboardingOutboxRepository;
import com.growthhub.user.repository.MenteeOnboardingRepository;
import com.growthhub.user.repository.MentorOnboardingOutboxRepository;
import com.growthhub.user.repository.MentorOnboardingRepository;
import com.growthhub.user.util.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class OnboardingKafkaFacade {

    private final KafkaProducer kafkaProducer;
    private final MenteeOnboardingRepository menteeOnboardingRepository;
    private final MenteeOnboardingOutboxRepository menteeOnboardingOutboxRepository;
    private final MentorOnboardingRepository mentorOnboardingRepository;
    private final MentorOnboardingOutboxRepository mentorOnboardingOutboxRepository;

    public void sendOnboardingComplete(Long outboxId, Role role) {
        menteeOnboardingOutboxRepository.findById(outboxId).ifPresent(onboardingOutbox -> {
            menteeOnboardingRepository.findById(onboardingOutbox.getOnboardingId()).ifPresent(onboarding -> {
                //auth-service로 onboarding-ok 메시지 전송(isOnboarded = true 변경)
                kafkaProducer.send("onboarding-mentee-ok",
                        OnboardingCompleteRequest.from(onboarding.getUserId(), role));
            });

            //outbox 삭제
            menteeOnboardingOutboxRepository.delete(onboardingOutbox);
        });
    }

    public void sendMentorOnboardingComplete(Long outboxId) {
        mentorOnboardingOutboxRepository.findById(outboxId).ifPresent(onboardingOutbox -> {
            mentorOnboardingRepository.findById(onboardingOutbox.getOnboardingId()).ifPresent(onboarding -> {
                //auth-service로 onboarding-ok 메시지 전송(isOnboarded = true 변경)
                kafkaProducer.send("onboarding-mentor-ok",
                        MentorOnboardingKafkaRequest.from(onboarding));
            });

            //outbox 삭제
            mentorOnboardingOutboxRepository.delete(onboardingOutbox);
        });
    }
}
