package com.growthhub.user.service;

import com.growthhub.user.domain.Onboarding;
import com.growthhub.user.domain.OnboardingOutbox;
import com.growthhub.user.dto.request.EnrollCareerRequest;
import com.growthhub.user.dto.request.OnboardingInfoRequest;
import com.growthhub.user.dto.response.RatingResponse;
import com.growthhub.user.repository.CareerRepository;
import com.growthhub.user.repository.OnboardingOutboxRepository;
import com.growthhub.user.repository.OnboardingRepository;
import com.growthhub.user.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final OnboardingRepository onboardingRepository;
    private final OnboardingOutboxRepository onboardingOutboxRepository;
    private final CareerRepository careerRepository;
    private final RatingRepository ratingRepository;

    @Transactional
    public Long onboardingComplete(Long userId, OnboardingInfoRequest onboardingInfoRequest) {
        Onboarding onboarding = onboardingRepository.save(onboardingInfoRequest.toOnboarding(userId));
        OnboardingOutbox outbox = onboardingOutboxRepository.save(OnboardingOutbox.from(onboarding));

        return outbox.getId();
    }

    @Transactional
    public void enrollCareer(Long userId, EnrollCareerRequest enrollCareerRequest) {
        careerRepository.save(enrollCareerRequest.toCareer(userId));
    }

    public List<RatingResponse> getRatings(List<Long> mentorIds) {
        List<Object[]> results = ratingRepository.findAverageRatingForUsers(mentorIds);
        return results.stream()
                .map(result -> RatingResponse.builder()
                        .mentorId((Long) result[0])  // mentorId is assumed to be at index 0
                        .rating(((Double) result[1]) != null ? Math.round((Double) result[1] * 2) / 2.0 : 0.0)
                        .build())
                .toList();
    }
}