package com.helarras.codingplatform.problem.internal.db;

import com.helarras.codingplatform.problem.internal.IProblemRepository;
import com.helarras.codingplatform.problem.internal.Problem;
import com.helarras.codingplatform.problem.internal.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProblemRepositoryAdapter implements IProblemRepository {
    private final SpringDataProblemRepository repository;
    private final ProblemMapper mapper;

    @Override
    public List<Problem> findPublished() {
        return repository.findAllByStatus(Status.PUBLISHED)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Problem save(Problem problem) {
        var entity = mapper.toEntity(problem);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Problem> findById(UUID id) {
        var entity = repository.findById(id);
        return entity.map(mapper::toDomain);
    }
}
