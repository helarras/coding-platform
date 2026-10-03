package com.helarras.codingplatform.submission.internal;

import com.helarras.codingplatform.execution.ExecutionService;
import com.helarras.codingplatform.execution.ExecutionResult;
import com.helarras.codingplatform.problem.TestCaseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class SubmissionEvaluator {
    private final ExecutionService execution;

    @Async
    public CompletableFuture<EvaluationResult> evaluate(String language, String sourceCode, Set<TestCaseDto> testCases) {
        var results = testCases.stream()
                .map((testCase) -> {
                    var execResult = execution.run(language, sourceCode, testCase.input());
                    return grade(execResult, testCase);
                })
                .toList();
        boolean passed = results.stream().allMatch(TestCaseResult::passed);
        if (passed)
            return CompletableFuture.completedFuture(EvaluationResult.accepted(results));
        return CompletableFuture.completedFuture(EvaluationResult.failed(results));
    }

    private TestCaseResult grade(ExecutionResult execResult, TestCaseDto testCase) {
        if (execResult.code() != 0)
            return TestCaseResult.builder()
                    .passed(false)
                    .input(testCase.input())
                    .actualOutput(execResult.output())
                    .errorOutput(execResult.error())
                    .expectedOutput(testCase.expectedOutput())
                    .build();
        if (!execResult.output().trim().equals(testCase.expectedOutput().trim()))
            return TestCaseResult.builder()
                    .passed(false)
                    .input(testCase.input())
                    .actualOutput(execResult.output())
                    .errorOutput(getErrorMessage(testCase, execResult.output()))
                    .expectedOutput(testCase.expectedOutput())
                    .build();

        // Passed
        return TestCaseResult.builder()
                .passed(true)
                .input(testCase.input())
                .expectedOutput(testCase.expectedOutput())
                .actualOutput(execResult.output())
                .errorOutput(execResult.error())
                .build();
    }

    private String getErrorMessage(TestCaseDto testCase, String actualOutput) {
        return String.format("Wrong answer on input [%s]. Expected: %s but got: %s", testCase.input(), testCase.expectedOutput(), actualOutput);

    }
}
