package com.helarras.codingplatform.submission.internal.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record SubmitRequest(
        @NotNull UUID userId,
        @NotNull UUID problemId,
        @NotBlank String language,
        @NotBlank String sourceCode
) {
}
