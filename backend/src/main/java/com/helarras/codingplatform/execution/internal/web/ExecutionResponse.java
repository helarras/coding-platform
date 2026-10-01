package com.helarras.codingplatform.execution.internal.web;

import lombok.Builder;

@Builder
public record ExecutionResponse(
        int code,
        String stdout,
        String stderr
) {
}
