package com.helarras.codingplatform.execution.internal;

public interface ICodeExecutor {

    ExecutionResult run(String language, String sourceCode, String stdInput);
}
