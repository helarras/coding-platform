package com.helarras.codingplatform.execution;

import com.helarras.codingplatform.execution.internal.ICodeExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExecutionService {

    private final ICodeExecutor executor;


//    private TestCaseResult gradeTestCase(ExecutionResult executionResult, TestCaseDto testCase) {
//
//        boolean result = executionResult.output().trim().equals(testCase.expectedOutput().trim());
//        return TestCaseResult.builder()
//                .passed(result)
//                .input(testCase.input())
//                .expectedOutput(testCase.expectedOutput())
//                .actualOutput(executionResult.output())
//                .errorOutput(executionResult.error())
//                .build();
//    }

    public List<ExecutionResult> runMultiple(String language, String sourceCode, List<String> inputs) {
        return inputs.stream()
                .map((input) -> executor.run(language, sourceCode, input))
                .toList();
    }

    public ExecutionResult run(String language, String sourceCode, String input) {
        return executor.run(language, sourceCode, input);
    }
}
