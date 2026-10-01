package com.helarras.codingplatform.problem;

import lombok.Builder;

@Builder
public record TestCaseDto(
        String input,
        String expectedOutput
) {
}
