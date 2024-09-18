package com.growthhub.user.util;

import com.growthhub.user.dto.request.OnboardingCompleteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, OnboardingCompleteDto message) {
        log.info("sending message={} to topic={}", message.userId(), topic);
        kafkaTemplate.send(topic, message);
    }
}
