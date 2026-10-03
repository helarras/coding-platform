package com.helarras.codingplatform.submission.internal.db;

import com.helarras.codingplatform.submission.internal.EvaluationResult;
import com.helarras.codingplatform.submission.internal.Submission;
import com.helarras.codingplatform.submission.internal.TestCaseResult;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public final class SubmissionMapper {


    public SubmissionEntity toEntity(Submission submission) {
        var entity = SubmissionEntity.builder()
                .id(submission.getId())
                .problemId(submission.getProblemId())
                .userId(submission.getUserId())
                .sourceCode(submission.getSourceCode())
                .status(submission.getResult().status())
                .build();

        var results = submission.getResult().testResults().stream()
                .map((result) -> toResultEntity(result, entity))
                .toList();
        entity.setTestResults(results);
        return entity;
    }

    public Submission toDomain(SubmissionEntity entity) {
        var results = entity.getTestResults().stream()
                .map(this::toTestCaseResult)
                .toList();
        return new Submission(
                entity.getId(),
                entity.getProblemId(),
                entity.getUserId(),
                entity.getSourceCode(),
                new EvaluationResult(entity.getStatus(), results));
    }

    public TestResultEntity toResultEntity(TestCaseResult result, SubmissionEntity submission) {
        return TestResultEntity.builder()
                .submission(submission)
                .passed(result.passed())
                .input(result.input())
                .expectedOutput(result.expectedOutput())
                .actualOutput(result.actualOutput())
                .errorOutput(result.errorOutput())
                .build();
    }

    public TestCaseResult toTestCaseResult(TestResultEntity entity) {
        return TestCaseResult.builder()
                .passed(entity.isPassed())
                .input(entity.getInput())
                .expectedOutput(entity.getExpectedOutput())
                .actualOutput(entity.getActualOutput())
                .errorOutput(entity.getErrorOutput())
                .build();
    }
}
