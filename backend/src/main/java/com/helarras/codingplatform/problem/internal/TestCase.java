package com.helarras.codingplatform.problem.internal;

import java.util.Objects;

public record TestCase(
        String input,
        String expectedOutput
) {

    public TestCase {
        if (input == null)
            throw new IllegalArgumentException("Test case input can't be null");
        if (expectedOutput == null)
            throw new IllegalArgumentException("expected output can't be null");
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TestCase(String input1, String output)))
            return false;
        return this.input.equals(input1) && this.expectedOutput.equals(output);
    }


    @Override
    public int hashCode() {
        return Objects.hash(input, expectedOutput);
    }
}
