package com.helarras.codingplatform.submission.internal;

import lombok.Builder;

@Builder
public record TestCaseResult(
        boolean passed,
        String input,
        String expectedOutput,
        String actualOutput,
        String errorOutput
) {}
