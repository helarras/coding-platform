package com.helarras.codingplatform.submission.internal;

import java.util.UUID;

public interface ISubmissionRepository {
    Submission save(Submission submission);
    Submission findById(UUID id);
}
