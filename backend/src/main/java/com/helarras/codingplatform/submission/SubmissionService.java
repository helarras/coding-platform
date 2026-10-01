package com.helarras.codingplatform.submission;

import com.helarras.codingplatform.execution.ExecutionService;
import com.helarras.codingplatform.execution.TestCaseResult;
import com.helarras.codingplatform.problem.ProblemService;
import com.helarras.codingplatform.problem.TestCaseDto;
import com.helarras.codingplatform.submission.internal.ISubmissionRepository;
import com.helarras.codingplatform.submission.internal.Submission;
import com.helarras.codingplatform.submission.internal.execution.CodeExecutionAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final ISubmissionRepository repository;
    private final ProblemService problemService;
    private final ExecutionService executionService;


    @Async
    protected void evaluateSubmission(UUID submissionId, String language, String sourceCode, Set<TestCaseDto> testCases) {
        List<TestCaseResult> results = executionService.evaluate(language, sourceCode, testCases);
        boolean accepted = true;
        String failReason = null;
        for (var result: results) {
            System.out.println(result);
            if (!result.passed()) {
                accepted = false;
                if (result.errorOutput() != null && !result.errorOutput().isBlank())
                    failReason = "Error: " + result.errorOutput();
                else
                    failReason = String.format("Wrong answer on input [%s]. Expected: %s but got: %s", result.input(), result.expectedOutput(), result.actualOutput());
                break;
            }
        }
        recordEvaluation(submissionId, accepted, failReason);
    }

    public UUID submitCode(UUID userId, UUID problemId, String sourceCode) {
        var testCases = problemService.getProblemTestCases(problemId);
        UUID submissionId = UUID.randomUUID();
        var submission = new Submission(submissionId, problemId, userId, sourceCode);
        var savedSubmission = repository.save(submission);
        evaluateSubmission(submissionId, "python", sourceCode, testCases);
        return savedSubmission.getId();
    }

    public void recordEvaluation(UUID submissionId, boolean passed, String failReason) {
        var submission = repository.findById(submissionId);
        if (submission == null)
            throw new RuntimeException("Can't find the submission with id: " + submissionId);
        if (passed) submission.markAsAccepted();
        else submission.markAsFailed(failReason);
        repository.save(submission);
    }

    public Submission getSubmission(UUID id) {
        return repository.findById(id);
    }

}
