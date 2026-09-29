package com.helarras.codingplatform.problem.internal.web;

import com.helarras.codingplatform.problem.internal.Difficulty;
import com.helarras.codingplatform.problem.internal.Status;
import com.helarras.codingplatform.problem.internal.TestCase;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ProblemResponse(
        UUID id,
        String title,
        String description,
        Status status,
        Difficulty difficulty,
        List<TestCase> examples
) {
}
