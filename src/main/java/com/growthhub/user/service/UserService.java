package com.growthhub.user.service;

import com.growthhub.user.domain.MenteeOnboardingInfo;
import com.growthhub.user.domain.MenteeOnboardingOutbox;
import com.growthhub.user.dto.request.EnrollCareerRequest;
import com.growthhub.user.dto.request.MenteeOnboardingInfoRequest;
import com.growthhub.user.dto.response.RatingResponse;
import com.growthhub.user.repository.CareerRepository;
import com.growthhub.user.repository.MenteeOnboardingOutboxRepository;
import com.growthhub.user.repository.MenteeOnboardingInfoRepository;
import com.growthhub.user.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final MenteeOnboardingInfoRepository menteeOnboardingInfoRepository;
    private final MenteeOnboardingOutboxRepository menteeOnboardingOutboxRepository;
    private final CareerRepository careerRepository;
    private final RatingRepository ratingRepository;

    @Transactional
    public Long menteeOnboardingComplete(Long userId, MenteeOnboardingInfoRequest menteeOnboardingInfoRequest) {
        MenteeOnboardingInfo menteeOnboardingInfo = menteeOnboardingInfoRepository.save(menteeOnboardingInfoRequest.toOnboarding(userId));
        MenteeOnboardingOutbox outbox = menteeOnboardingOutboxRepository.save(MenteeOnboardingOutbox.from(menteeOnboardingInfo));

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
                        .rating(result[1] != null ? Math.round((Double) result[1] * 2) / 2.0 : 0.0)
                        .build())
                .toList();
    }
}
