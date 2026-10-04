package com.helarras.codingplatform.submission.internal;

import java.util.Optional;
import java.util.UUID;

public interface ISubmissionRepository {
    Submission save(Submission submission);
    Optional<Submission> findById(UUID id);
}
