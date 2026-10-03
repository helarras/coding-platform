package com.helarras.codingplatform.execution;

import lombok.Builder;

@Builder
public record TestCaseResult(
        boolean passed,
        String input,
        String expectedOutput,
        String actualOutput,
        String errorOutput
) {

    @Override
    public String toString() {
        return "TestCaseResult{" +
                "passed=" + passed +
                ", input='" + input + '\'' +
                ", expectedOutput='" + expectedOutput + '\'' +
                ", actualOutput='" + actualOutput + '\'' +
                ", errorOutput='" + errorOutput + '\'' +
                '}';
    }
}
