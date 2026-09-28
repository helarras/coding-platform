package com.helarras.codingplatform.submission.internal.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataSubmissionRepository extends CrudRepository<SubmissionEntity, UUID> {
}
