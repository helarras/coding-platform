package com.helarras.codingplatform.problem.internal.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TestCaseRequest(
        @NotBlank String input,
        @NotBlank String expectedOutput
) {
}
