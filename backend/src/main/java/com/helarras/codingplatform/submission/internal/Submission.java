package com.helarras.codingplatform.submission.internal;

import lombok.Getter;

import java.util.UUID;

public class Submission {
    @Getter private final UUID id;
    @Getter private UUID problemId;
    @Getter private UUID userId;
    @Getter private String sourceCode;
    @Getter private EvaluationResult result;

    public Submission(UUID id, UUID problemId, UUID userId, String sourceCode, EvaluationResult result) {
        this.id = id;
        this.problemId = problemId;
        this.userId = userId;
        this.sourceCode = sourceCode;
        this.result = result;
    }

    public Submission(UUID id, UUID problemId, UUID userId, String sourceCode) {
        this(id, problemId, userId, sourceCode, EvaluationResult.pending());
    }

    public void markAsAccepted() {
        if (result.status() != Status.PENDING)
            throw new RuntimeException("This submission is already evaluated");
        this.result = EvaluationResult.accepted();
    }

    public void markAsFailed(String reason) {
        if (result.status() != Status.PENDING)
            throw new RuntimeException("This submission is already evaluated");
        if (reason == null || reason.isBlank())
            throw new RuntimeException("The failure reason is required");
        this.result = EvaluationResult.failed(reason);
    }

}
