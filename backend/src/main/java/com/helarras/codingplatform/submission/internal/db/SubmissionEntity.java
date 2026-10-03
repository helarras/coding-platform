package com.helarras.codingplatform.submission.internal.db;

import com.helarras.codingplatform.submission.internal.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "submissions")
public class SubmissionEntity {
    @Id
    private UUID id;
    private UUID userId;
    private UUID problemId;
    private String sourceCode;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestResultEntity> testResults;
}
