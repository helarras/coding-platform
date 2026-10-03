package com.helarras.codingplatform.submission;

import com.helarras.codingplatform.execution.ExecutionService;
import com.helarras.codingplatform.submission.internal.*;
import com.helarras.codingplatform.problem.ProblemService;
import com.helarras.codingplatform.problem.TestCaseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final ISubmissionRepository repository;
    private final ProblemService problemService;
    private final SubmissionEvaluator evaluator;



    public UUID submitCode(UUID userId, UUID problemId, String language, String sourceCode) {
        var testCases = problemService.getProblemTestCases(problemId);
        UUID submissionId = UUID.randomUUID();
        var submission = new Submission(submissionId, problemId, userId, sourceCode);
        var savedSubmission = repository.save(submission);
        CompletableFuture<EvaluationResult> future = evaluator.evaluate(language, sourceCode, testCases);
        future.thenAccept((evaResult) -> recordEvaluation(submissionId, evaResult));
        return savedSubmission.getId();
    }

    public void recordEvaluation(UUID submissionId, EvaluationResult evaResult) {
        var submission = repository.findById(submissionId);
        if (submission == null)
            throw new RuntimeException("Can't find the submission with id: " + submissionId);
        submission.recordEvaluation(evaResult);
        repository.save(submission);
    }

    public Submission getSubmission(UUID id) {
        return repository.findById(id);
    }

}
