package com.helarras.codingplatform.problem.internal.web;

import com.helarras.codingplatform.problem.ProblemService;
import com.helarras.codingplatform.problem.internal.Difficulty;
import com.helarras.codingplatform.problem.internal.TestCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService service;


    @GetMapping
    public ResponseEntity<List<ProblemResponse>> publishedProblems() {
        return ResponseEntity.ok(service.publishedProblems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemResponse> getProblem(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getProblem(id));
    }

    @PostMapping
    public ResponseEntity<ProblemResponse> addProblem(@RequestBody ProblemRequest body) {
        return new ResponseEntity<>(service.createProblem(body.title(), body.description()), HttpStatus.CREATED);
    }


    @PostMapping("/{id}/publish")
    public ResponseEntity<ProblemResponse> publishProblem(@PathVariable UUID id) {
        return ResponseEntity.ok(service.publishProblem(id));
    }

    @PostMapping("/{id}/testcase/new")
    public ResponseEntity<ProblemResponse> createTestCase(@PathVariable UUID id, @RequestBody TestCaseRequest body) {
        return ResponseEntity.ok(service.addTestCase(id, body.input(), body.expectedOutput()));
    }

    @DeleteMapping("/{id}/testcase/remove")
    public ResponseEntity<ProblemResponse> removeTestCase(@PathVariable UUID id, @RequestBody TestCaseRequest body) {
        return ResponseEntity.ok(service.removeTestCase(id, body.input(), body.expectedOutput()));
    }

    @PostMapping("/{id}/difficulty")
    public ResponseEntity<ProblemResponse> assignDifficulty(@PathVariable UUID id, @RequestParam Difficulty difficulty) {
        return ResponseEntity.ok(service.updateDifficulty(id, difficulty));
    }

    @GetMapping("/{id}/testcases")
    public ResponseEntity<Set<TestCase>> problemTestCases(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getProblemTestCases(id));
    }
}
