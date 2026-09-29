package com.helarras.codingplatform.problem.internal.web;

import lombok.Builder;

@Builder
public record TestCaseRequest(
        String input,
        String expectedOutput
) {
}
