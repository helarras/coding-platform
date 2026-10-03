package com.helarras.codingplatform.submission.internal.db;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "submission_testcase_results")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id")
    private SubmissionEntity submission;

    private boolean passed;
    private String input;
    private String expectedOutput;
    private String actualOutput;
    private String errorOutput;
}
