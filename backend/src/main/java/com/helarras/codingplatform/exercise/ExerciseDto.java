package com.helarras.codingplatform.exercise;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ExerciseDto(
        UUID authorId,
        String title,
        String descriptionMd,
        String starterCode
) {
}
