package com.helarras.codingplatform.submission.internal;

import java.util.Optional;

public record EvaluationResult(
        Status status,
        Optional<String> failReason
) {

    public EvaluationResult {
        if (status != Status.FAILED && failReason.isPresent())
            throw new RuntimeException("A non failed result can't have a fail reason");
        if (status == Status.FAILED && failReason.isEmpty())
            throw new RuntimeException("A failed result must have a fail reason");
    }

    public static EvaluationResult accepted() {
        return new EvaluationResult(Status.ACCEPTED, Optional.empty());
    }

    public static EvaluationResult failed(String reason) {
        return new EvaluationResult(Status.FAILED, Optional.of(reason));
    }

    public static EvaluationResult pending() {
        return new EvaluationResult(Status.PENDING, Optional.empty());
    }
}

