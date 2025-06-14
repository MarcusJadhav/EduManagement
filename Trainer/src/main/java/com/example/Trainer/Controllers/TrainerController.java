package com.example.Trainer.Controllers;

import com.example.Trainer.DTO.TrainerRequest;
import com.example.Trainer.DTO.TrainerResponse;
import com.example.Trainer.Services.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @PostMapping
    public ResponseEntity<TrainerResponse> addTrainer(@RequestBody TrainerRequest request) {
      return ResponseEntity.ok(trainerService.addTrainer(request));
    }

    @GetMapping
    public ResponseEntity<List<TrainerResponse>> getAll() {
        return ResponseEntity.ok(trainerService.getAllTrainers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(trainerService.getTrainerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerResponse> update(@PathVariable Long id, @RequestBody TrainerRequest request) {
        return ResponseEntity.ok(trainerService.updateTrainer(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        return ResponseEntity.noContent().build();
    }
}
