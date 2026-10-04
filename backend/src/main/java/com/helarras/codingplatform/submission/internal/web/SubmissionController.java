package com.helarras.codingplatform.submission.internal.web;

import com.helarras.codingplatform.submission.SubmissionService;
import com.helarras.codingplatform.submission.internal.Submission;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {
    private final SubmissionService service;


    @PostMapping
    public UUID submit(@RequestBody @Valid SubmitRequest body) {
        return service.submitCode(body.userId(), body.problemId(), body.language(), body.sourceCode());
    }

    @GetMapping("/{id}")
    public Submission fetchSubmission(@PathVariable UUID id) {
        return service.getSubmission(id);
    }
}
