package com.helarras.codingplatform.submission.internal.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringDataSubmissionRepository extends CrudRepository<SubmissionEntity, UUID> {

    List<SubmissionEntity> findAllByProblemIdAndUserId(UUID problemId, UUID userId);
}
