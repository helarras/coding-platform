package com.helarras.codingplatform.submission;

import com.helarras.codingplatform.submission.internal.ISubmissionRepository;
import com.helarras.codingplatform.submission.internal.Submission;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubmissionService {

    private final ISubmissionRepository repository;

    public SubmissionService(ISubmissionRepository repository) {
        this.repository = repository;
    }


    public UUID submitCode(UUID userId, UUID problemId, String sourceCode) {
        UUID submissionId = UUID.randomUUID();
        var submission = new Submission(submissionId, problemId, userId, sourceCode);
        var savedSubmission = repository.save(submission);
        return savedSubmission.getId();
    }

    public void recordEvaluation(UUID submissionId, boolean passed, String failReason) {
        var submission = repository.findById(submissionId);
        if (submission == null)
            throw new RuntimeException("Can't find the submission with id: " + submissionId);
        if (passed) submission.markAsAccepted();
        else submission.markAsFailed(failReason);
        repository.save(submission);
    }

    public Submission getSubmission(UUID id) {
        return repository.findById(id);
    }

}
