package com.helarras.codingplatform.submission.internal.db;

import com.helarras.codingplatform.common.exception.ResourceNotFoundException;
import com.helarras.codingplatform.submission.internal.ISubmissionRepository;
import com.helarras.codingplatform.submission.internal.Submission;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
public class SubmissionRepositoryAdapter implements ISubmissionRepository {
    private final SpringDataSubmissionRepository repository;
    private final SubmissionMapper mapper;

    public SubmissionRepositoryAdapter(SpringDataSubmissionRepository repository, SubmissionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public Submission save(Submission submission) {
        var entity = mapper.toEntity(submission);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional
    public Optional<Submission> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }
}
