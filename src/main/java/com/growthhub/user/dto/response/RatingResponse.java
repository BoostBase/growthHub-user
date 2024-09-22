package com.growthhub.user.dto.response;

import lombok.Builder;

@Builder
public record RatingResponse(
        Long mentorId,
        Double rating
) {

}
