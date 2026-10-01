package com.helarras.codingplatform.execution;

import com.helarras.codingplatform.execution.internal.ExecutionResult;
import com.helarras.codingplatform.execution.internal.ICodeExecutor;
import com.helarras.codingplatform.problem.TestCaseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ExecutionService {

    private final ICodeExecutor executor;


    private TestCaseResult gradeTestCase(ExecutionResult executionResult, TestCaseDto testCase) {

        boolean result = executionResult.output().trim().equals(testCase.expectedOutput().trim());
        return TestCaseResult.builder()
                .passed(result)
                .input(testCase.input())
                .expectedOutput(testCase.expectedOutput())
                .actualOutput(executionResult.output())
                .errorOutput(executionResult.error())
                .build();
    }

    public List<TestCaseResult> evaluate(String language, String sourceCode, Set<TestCaseDto> testCases) {
        return testCases.stream()
                .map((testCase) -> {
                    var executionResult = executor.run(language, sourceCode, testCase.input());
                    return gradeTestCase(executionResult, testCase);
                })
                .toList();
    }

    public ExecutionResult run(String language, String sourceCode, String input) {
        return executor.run(language, sourceCode, input);
    }
}
