package com.growthhub.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "career")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "association", nullable = false)
    private String association;

    @Column(name = "enter_date", nullable = false)
    private LocalDate enterDate;

    @Column(name = "retire_date", nullable = false)
    private LocalDate retireDate;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified;

    @Column(name = "part", nullable = false)
    private String part;

    @Builder
    public Career(Long userId, String association, LocalDate enterDate, LocalDate retireDate, Boolean isVerified,
                  String part) {
        this.userId = userId;
        this.association = association;
        this.enterDate = enterDate;
        this.retireDate = retireDate;
        this.isVerified = isVerified;
        this.part = part;
    }
}
