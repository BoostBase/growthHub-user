package com.growthhub.user.controller;

import com.growthhub.user.dto.ResponseTemplate;
import com.growthhub.user.dto.request.EnrollCareerRequest;
import com.growthhub.user.dto.request.OnboardingInfoRequest;
import com.growthhub.user.dto.response.RatingResponse;
import com.growthhub.user.service.UserService;
import com.growthhub.user.service.UserServiceFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "UserController", description = "user 관련 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping
@RestController
public class UserController {

    private final UserServiceFacade userServiceFacade;
    private final UserService userService;

    @Operation(summary = "onboarding", description = "onboarding<br>타입 종류: COMPANY_SIZE, MENTOR_TYPE,PURPOSE, ADDITIONAL_DETAIL")
    @PostMapping("/onboarding")
    public ResponseEntity<ResponseTemplate<?>> onboarding(
            HttpServletRequest request,
            @RequestBody OnboardingInfoRequest onboardingInfoRequest
    ) {
        Long userId = Long.parseLong(request.getHeader("User-Id"));
        userServiceFacade.onboardingComplete(userId, onboardingInfoRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.EMPTY_RESPONSE);
    }

    @Operation(summary = "enroll career", description = "enroll career")
    @PostMapping("/career")
    public ResponseEntity<ResponseTemplate<?>> enrollCareer(
            HttpServletRequest request,
            @RequestBody EnrollCareerRequest enrollCareerRequest
    ) {
        Long userId = Long.parseLong(request.getHeader("User-Id"));
        userService.enrollCareer(userId, enrollCareerRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.EMPTY_RESPONSE);
    }

    @GetMapping("/rating/average")
    public ResponseEntity<List<RatingResponse>> getRatings(
            @RequestParam("mentorIds") List<Long> mentorIds
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getRatings(mentorIds));
    }
}
