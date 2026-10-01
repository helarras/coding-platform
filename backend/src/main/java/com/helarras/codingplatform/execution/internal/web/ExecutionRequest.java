package com.helarras.codingplatform.execution.internal.web;

import lombok.Builder;

@Builder
public record ExecutionRequest(
        String language,
        String sourceCode,
        String input
) {
}
