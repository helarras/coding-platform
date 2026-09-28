package com.helarras.codingplatform.submission.internal.db;

import com.helarras.codingplatform.submission.internal.ISubmissionRepository;
import com.helarras.codingplatform.submission.internal.Submission;
import org.springframework.stereotype.Component;

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
    public Submission findById(UUID id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission entity can't be found: " + id));
        return mapper.toDomain(entity);
    }
}
