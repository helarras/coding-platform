package com.helarras.codingplatform.execution.internal.web;

import com.helarras.codingplatform.execution.ExecutionService;
import com.helarras.codingplatform.execution.internal.ExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/execute")
@RequiredArgsConstructor
public class ExecutionController {

    private final ExecutionService service;

    @PostMapping
    public ResponseEntity<ExecutionResponse> runCode(@RequestBody ExecutionRequest body) {
        ExecutionResult result = service.run(body.language(), body.sourceCode(), body.input());
        return ResponseEntity.ok(
                ExecutionResponse.builder()
                        .code(result.code())
                        .stdout(result.output())
                        .stderr(result.error())
                        .build()
        );
    }
}
