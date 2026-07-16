package com.helarras.codingplatform.exercise.internal;

import com.helarras.codingplatform.exercise.ExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;


    @PostMapping
    public ResponseEntity<ExerciseDto> createExercise(@RequestBody ExerciseDto exerciseDto) {
        return ResponseEntity.ok(exerciseService.createExercise(exerciseDto));
    }


    @PutMapping
    public ResponseEntity<ExerciseDto> updateExercise(@RequestBody ExerciseDto exerciseDto) {
        return ResponseEntity.ok(exerciseService.updateExercise(exerciseDto.authorId(), exerciseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDto> getExerciseById(@PathVariable String id) {
        return ResponseEntity.ok(exerciseService.getExerciseById(id));
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<?> getAllExercisesByAuthor(@PathVariable String id) {
        return ResponseEntity.ok(exerciseService.getAllExercisesByAuthor(id));
    }
}
