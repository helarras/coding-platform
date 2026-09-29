package com.helarras.codingplatform.problem.internal;

import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Problem {
    @Getter private final UUID id;
    @Getter private String title;
    @Getter private String description;
    @Getter private final Set<TestCase> testCases;
    @Getter private Status status;
    @Getter private Difficulty difficulty;

    public Problem(UUID id, String title, String description, Status status, Difficulty difficulty, Set<TestCase> testCases) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.testCases = testCases;
        this.status = status;
        this.difficulty = difficulty;
    }

    public Problem(UUID id, String title, String description, Set<TestCase> testCases) {
        this(id, title, description, Status.DRAFT, Difficulty.UNDEFINED, testCases);
    }

    public Problem(UUID id, String title, String description) {
        this(id, title, description, Status.DRAFT, Difficulty.UNDEFINED, new HashSet<>());
    }

    public void publish() {
        if (status == Status.PUBLISHED)
            throw new RuntimeException("This problem is already published");
        if (testCases.isEmpty())
            throw new RuntimeException("A problem must have at least 1 test cases to be published");
        if (difficulty == Difficulty.UNDEFINED)
            throw new RuntimeException("A problem cannot be published unless an Admin has explicitly confirmed its difficulty level.");
        this.status = Status.PUBLISHED;
    }

    public void setDifficulty(Difficulty difficulty) {
        if (difficulty == Difficulty.UNDEFINED && status == Status.PUBLISHED)
            status = Status.DRAFT;
        this.difficulty = difficulty;
    }

    public void addTestCase(TestCase testCase) {
        testCases.add(testCase);
    }

    public void removeTestCase(TestCase testCase) {
        testCases.remove(testCase);
        if (testCases.isEmpty())
            status = Status.DRAFT;
    }


    public List<TestCase> getVisibleTestCases() {
        return testCases.stream().limit(3).toList();
    }
}
