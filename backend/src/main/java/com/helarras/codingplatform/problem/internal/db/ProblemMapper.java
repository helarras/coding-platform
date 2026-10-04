package com.helarras.codingplatform.problem.internal.db;

import com.helarras.codingplatform.problem.TestCaseDto;
import com.helarras.codingplatform.problem.internal.Problem;
import com.helarras.codingplatform.problem.internal.TestCase;
import com.helarras.codingplatform.problem.internal.web.ProblemResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public final class ProblemMapper {


    public Problem toDomain(ProblemEntity entity) {
        var testCases = entity.getTestCases()
                .stream()
                .map(this::toTestCaseDomain)
                .collect(Collectors.toSet());
        return new Problem(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getDifficulty(),
                testCases
        );
    }

    public TestCase toTestCaseDomain(TestCaseEntity entity) {
        return new TestCase(
                entity.getInput(),
                entity.getExpectedOutput()
        );
    }

    public ProblemEntity toEntity(Problem problem) {
        var entity = ProblemEntity.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .description(problem.getDescription())
                .status(problem.getStatus())
                .difficulty(problem.getDifficulty())
                .build();
        var testCaseEntities = problem.getTestCases()
                .stream()
                .map((testCase -> toTestCaseEntity(testCase, entity)))
                .collect(Collectors.toSet());
        entity.setTestCases(testCaseEntities);
        return entity;
    }

    public TestCaseEntity toTestCaseEntity(TestCase testCase, ProblemEntity entity) {
        return TestCaseEntity.builder()
                .problem(entity)
                .input(testCase.input())
                .expectedOutput(testCase.expectedOutput())
                .build();
    }

    public ProblemResponse toResponse(Problem problem) {
        return ProblemResponse.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .description(problem.getDescription())
                .status(problem.getStatus())
                .difficulty(problem.getDifficulty())
                .examples(problem.getVisibleTestCases())
                .build();
    }

    public TestCaseDto toTestCaseDto(TestCase testCase) {
        return TestCaseDto.builder()
                .input(testCase.input())
                .expectedOutput(testCase.expectedOutput())
                .build();
    }
}
