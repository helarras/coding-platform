package com.helarras.codingplatform.submission.internal.db;

import com.helarras.codingplatform.submission.internal.EvaluationResult;
import com.helarras.codingplatform.submission.internal.Submission;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public final class SubmissionMapper {


    public SubmissionEntity toEntity(Submission submission) {
        return SubmissionEntity.builder()
                .id(submission.getId())
                .problemId(submission.getProblemId())
                .userId(submission.getUserId())
                .sourceCode(submission.getSourceCode())
                .status(submission.getResult().status())
                .failReason(submission.getResult().failReason().orElse(null))
                .build();
    }

    public Submission toDomain(SubmissionEntity entity) {
        return new Submission(
                entity.getId(),
                entity.getProblemId(),
                entity.getUserId(),
                entity.getSourceCode(),
                new EvaluationResult(entity.getStatus(), Optional.ofNullable(entity.getFailReason())));
    }
}
