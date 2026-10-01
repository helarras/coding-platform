package com.helarras.codingplatform.execution.internal;

import lombok.Builder;

@Builder
public record PistonResponse(
        String language,
        String version,
        PistonRun run
) {
    public record PistonRun(
            String stdout,
            String stderr,
            int code,
            String signal,
            String output
    ) {}
}
