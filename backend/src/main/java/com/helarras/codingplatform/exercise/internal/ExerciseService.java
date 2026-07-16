package com.helarras.codingplatform.exercise.internal;

import com.helarras.codingplatform.exercise.ExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository repository;

    private final ExerciseMapper mapper;

    public ExerciseDto createExercise(ExerciseDto exerciseDto) {
        var exercise = mapper.toEntity(exerciseDto);
        var savedExercise = repository.save(exercise);
        return mapper.toDTO(savedExercise);
    }

    public ExerciseDto getExerciseById(String id) {
        var exercise = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Exercise not found"));
        return mapper.toDTO(exercise);
    }

    public ExerciseDto updateExercise(UUID id, ExerciseDto exerciseDto) {
        var existingExercise = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        existingExercise.setTitle(exerciseDto.title());
        existingExercise.setDescriptionMd(exerciseDto.descriptionMd());
        existingExercise.setStarterCode(exerciseDto.starterCode());

        var updatedExercise = repository.save(existingExercise);
        return mapper.toDTO(updatedExercise);
    }

    public List<ExerciseDto> getAllExercisesByAuthor(String authorId) {
        var exercises = repository.findAllByAuthorId(UUID.fromString(authorId));
        return exercises.stream()
                .map(mapper::toDTO)
                .toList();
    }
}
