package com.helarras.codingplatform.submission.internal.web;

import lombok.Builder;

@Builder
public record EvaluateRequest(
        boolean passed,
        String reason
) {
}
