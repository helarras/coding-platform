package com.helarras.codingplatform.execution.internal.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ExecutionRequest(
        @NotBlank String language,
        @NotBlank String sourceCode,
        String input
) {
}
