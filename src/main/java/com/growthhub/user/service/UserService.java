package com.growthhub.user.service;

import com.growthhub.user.domain.MenteeOnboarding;
import com.growthhub.user.domain.MenteeOnboardingOutbox;
import com.growthhub.user.domain.MentorOnboarding;
import com.growthhub.user.domain.MentorOnboardingOutbox;
import com.growthhub.user.dto.request.EnrollCareerRequest;
import com.growthhub.user.dto.request.MenteeOnboardingInfoRequest;
import com.growthhub.user.dto.request.MentorOnboardingCareerRequest;
import com.growthhub.user.dto.request.MentorOnboardingRequest;
import com.growthhub.user.dto.response.RatingResponse;
import com.growthhub.user.repository.CareerRepository;
import com.growthhub.user.repository.MenteeOnboardingOutboxRepository;
import com.growthhub.user.repository.MenteeOnboardingRepository;
import com.growthhub.user.repository.MentorOnboardingOutboxRepository;
import com.growthhub.user.repository.MentorOnboardingRepository;
import com.growthhub.user.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final MenteeOnboardingRepository menteeOnboardingRepository;
    private final MenteeOnboardingOutboxRepository menteeOnboardingOutboxRepository;
    private final CareerRepository careerRepository;
    private final RatingRepository ratingRepository;
    private final MentorOnboardingRepository mentorOnboardingRepository;
    private final MentorOnboardingOutboxRepository mentorOnboardingOutboxRepository;

    @Transactional
    public Long menteeOnboardingComplete(Long userId, MenteeOnboardingInfoRequest menteeOnboardingInfoRequest) {
        MenteeOnboarding menteeOnboarding =
                menteeOnboardingRepository.save(menteeOnboardingInfoRequest.toOnboarding(userId));
        MenteeOnboardingOutbox outbox =
                menteeOnboardingOutboxRepository.save(MenteeOnboardingOutbox.from(menteeOnboarding));

        return outbox.getId();
    }

    @Transactional
    public Long mentorOnboardingComplete(Long userId, MentorOnboardingRequest mentorOnboardingInfoRequest) {
        MentorOnboarding mentorOnboarding =
                mentorOnboardingRepository.save(mentorOnboardingInfoRequest.toMentorOnboarding(userId));

        for (MentorOnboardingCareerRequest career : mentorOnboardingInfoRequest.careers()) {
            careerRepository.save(career.toCareer(userId));
        }

        MentorOnboardingOutbox outbox =
                mentorOnboardingOutboxRepository.save(MentorOnboardingOutbox.from(mentorOnboarding));

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
