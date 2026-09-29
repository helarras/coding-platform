package com.helarras.codingplatform.problem.internal.web;

import lombok.Builder;

@Builder
public record ProblemRequest(
        String title,
        String description
) {
}
