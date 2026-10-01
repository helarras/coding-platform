package com.helarras.codingplatform.execution.internal;

import lombok.Builder;

@Builder
public record ExecutionResult(
        int code,
        String output,
        String error
) {

    public static ExecutionResult failure(String errorMessage) {
        return new ExecutionResult(-1, "", errorMessage);
    }
}
