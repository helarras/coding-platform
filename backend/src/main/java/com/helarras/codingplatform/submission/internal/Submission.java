package com.helarras.codingplatform.submission.internal;

import com.helarras.codingplatform.common.exception.IllegalSubmissionStateException;
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


    public void recordEvaluation(EvaluationResult result) {
        if (!this.result.status().equals(Status.PENDING))
            throw new IllegalSubmissionStateException("Submission is already evaluated");
        this.result = result;
    }

}
