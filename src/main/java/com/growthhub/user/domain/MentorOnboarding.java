package com.growthhub.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "mentor_onboarding")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MentorOnboarding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "association", nullable = false)
    private String association;

    @Column(name = "part", nullable = false)
    private String part;

    @Column(name = "detail_part", nullable = false)
    private String detailPart;

    @Column(name = "career_year", nullable = false)
    private Long careerYear;

    @Builder
    public MentorOnboarding(Long userId, String part, String detailPart, Long careerYear, String association) {
        this.userId = userId;
        this.part = part;
        this.detailPart = detailPart;
        this.careerYear = careerYear;
        this.association = association;
    }
}
