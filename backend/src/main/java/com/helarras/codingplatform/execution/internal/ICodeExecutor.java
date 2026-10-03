package com.helarras.codingplatform.execution.internal;

import com.helarras.codingplatform.execution.ExecutionResult;

public interface ICodeExecutor {

    ExecutionResult run(String language, String sourceCode, String stdInput);
}
