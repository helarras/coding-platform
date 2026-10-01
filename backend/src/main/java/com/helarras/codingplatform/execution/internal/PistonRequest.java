package com.helarras.codingplatform.execution.internal;

import lombok.Builder;

@Builder
public record PistonRequest(
        String language,
        String version,
        String stdin,
        PistonFile[] files
) {

    public record PistonFile(
            String name,
            String content
    ) {}
}
