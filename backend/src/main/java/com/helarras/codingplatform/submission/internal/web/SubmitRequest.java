package com.helarras.codingplatform.submission.internal.web;

import lombok.Builder;

import java.util.UUID;

@Builder
public record SubmitRequest(
        UUID userId,
        UUID problemId,
        String language,
        String sourceCode
) {
}
