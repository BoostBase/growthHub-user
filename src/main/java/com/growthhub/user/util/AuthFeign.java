package com.growthhub.user.util;

import com.growthhub.user.domain.type.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTH-SERVICE")
public interface AuthFeign {
    @GetMapping("/mentee-onboarding")
    ResponseEntity<Void> menteeOnboarding(@RequestParam Long userId, @RequestParam Role role);

    @GetMapping("/mentor-onboarding")
    ResponseEntity<Void> mentorOnboarding(@RequestParam Long userId,
                                          @RequestParam String association,
                                          @RequestParam String part,
                                          @RequestParam Long careerYear);
}