package com.helarras.codingplatform.exercise.internal;

import com.helarras.codingplatform.exercise.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {

    List<Exercise> findAllByAuthorId(UUID authorId);
}
