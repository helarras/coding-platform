package com.helarras.codingplatform.exercise.internal;

import com.helarras.codingplatform.exercise.Exercise;
import com.helarras.codingplatform.exercise.ExerciseDto;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {

    public Exercise toEntity(ExerciseDto exerciseDto) {
        return Exercise.builder()
                .authorId(exerciseDto.authorId())
                .title(exerciseDto.title())
                .descriptionMd(exerciseDto.descriptionMd())
                .starterCode(exerciseDto.starterCode())
                .build();
    }

    public ExerciseDto toDTO(Exercise exercise) {
        return ExerciseDto.builder()
                .authorId(exercise.getAuthorId())
                .title(exercise.getTitle())
                .descriptionMd(exercise.getDescriptionMd())
                .starterCode(exercise.getStarterCode())
                .build();
    }
}
