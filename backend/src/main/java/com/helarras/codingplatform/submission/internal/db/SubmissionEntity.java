package com.helarras.codingplatform.submission.internal.db;

import com.helarras.codingplatform.submission.internal.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "submissions")
public class SubmissionEntity {
    @Id
    private UUID id;
    private UUID userId;
    private UUID problemId;
    private String sourceCode;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String failReason;
}
