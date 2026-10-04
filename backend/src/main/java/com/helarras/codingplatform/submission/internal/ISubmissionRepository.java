package com.helarras.codingplatform.submission.internal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISubmissionRepository {
    Submission save(Submission submission);
    Optional<Submission> findById(UUID id);
    List<Submission> findAllByProblemIdAndUserId(UUID problemId, UUID userId);
}
