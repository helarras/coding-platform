package com.helarras.codingplatform.problem.internal.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ProblemRequest(
        @NotBlank @Size(max = 120)
        String title,
        @NotBlank @Size(max = 3600)
        String description
) {
}
