package com.helarras.codingplatform.submission.internal;

import java.util.Collections;
import java.util.List;

public record EvaluationResult(
        Status status,
        List<TestCaseResult> testResults
) {

    public static EvaluationResult accepted(List<TestCaseResult> testResults) {
        return new EvaluationResult(Status.ACCEPTED, testResults);
    }

    public static EvaluationResult failed(List<TestCaseResult> testResults) {
        return new EvaluationResult(Status.FAILED, testResults);
    }

    public static EvaluationResult pending() {
        return new EvaluationResult(Status.PENDING, Collections.emptyList());
    }
}

