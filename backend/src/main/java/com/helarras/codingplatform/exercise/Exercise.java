package com.helarras.codingplatform.exercise;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity(name = "exercises")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "author_id", nullable = true)
    private UUID authorId;

    @Column(nullable = false)
    private String title;

    @Column(name = "description_markdown", nullable = false)
    private String descriptionMd;

    @Column(name = "starter_code")
    private String starterCode;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;
}
