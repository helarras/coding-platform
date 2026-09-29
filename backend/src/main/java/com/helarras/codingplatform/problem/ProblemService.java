package com.helarras.codingplatform.problem;

import com.helarras.codingplatform.problem.internal.Difficulty;
import com.helarras.codingplatform.problem.internal.IProblemRepository;
import com.helarras.codingplatform.problem.internal.Problem;
import com.helarras.codingplatform.problem.internal.TestCase;
import com.helarras.codingplatform.problem.internal.db.ProblemMapper;
import com.helarras.codingplatform.problem.internal.web.ProblemResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ProblemService {

    private final IProblemRepository repository;
    private final ProblemMapper mapper;

    public ProblemService(IProblemRepository repository, ProblemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProblemResponse> publishedProblems() {
        return repository.findPublished()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ProblemResponse createProblem(String title, String description) {
        var problemId = UUID.randomUUID();
        var problem = new Problem(problemId, title, description);
        return mapper.toResponse(repository.save(problem));
    }

    public ProblemResponse getProblem(UUID id) {
        var problem =  repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find problem with id: " + id));
        return mapper.toResponse(problem);
    }

    public ProblemResponse publishProblem(UUID id) {
        var problem = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find problem with id: " + id));
        problem.publish();
        return mapper.toResponse(repository.save(problem));
    }

    public ProblemResponse addTestCase(UUID problemId, String input, String expectedOutput) {
        var problem = repository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Can't find problem with id: " + problemId));
        problem.addTestCase(new TestCase(input, expectedOutput));
        return mapper.toResponse(repository.save(problem));
    }

    public ProblemResponse removeTestCase(UUID problemId, String input, String expectedOutput) {
        var problem = repository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Can't find problem with id: " + problemId));
        problem.removeTestCase(new TestCase(input, expectedOutput));
        return mapper.toResponse(repository.save(problem));
    }

    public ProblemResponse updateDifficulty(UUID problemId, Difficulty difficulty) {
        var problem = repository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Can't find problem with id: " + problemId));
        problem.setDifficulty(difficulty);
        return mapper.toResponse(repository.save(problem));
    }

    public Set<TestCase> getProblemTestCases(UUID problemId) {
        var problem = repository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Can't find problem with id: " + problemId));
        return problem.getTestCases();
    }



}
