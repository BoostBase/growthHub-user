package com.growthhub.user.controller;

import com.growthhub.user.dto.request.OnboardingCompleteDto;
import com.growthhub.user.util.KafkaProducer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/test")
@RestController
public class TestController {

    private final KafkaProducer kafkaProducer;

    @GetMapping
    public String test(HttpServletRequest request) {
        return request.getHeader("User-Id");
    }

    @GetMapping("/test-string")
    public String test2() {
        return "test";
    }

    @GetMapping("/kafka-test")
    public String kafkaTest(HttpServletRequest request) {
        kafkaProducer.send("test", new OnboardingCompleteDto(Long.valueOf(request.getHeader("User-Id"))));
        return "kafka-test";
    }
}