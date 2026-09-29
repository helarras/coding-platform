package com.helarras.codingplatform.problem.internal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProblemRepository {
    List<Problem> findPublished();
    Problem save(Problem problem);
    Optional<Problem> findById(UUID id);
}
