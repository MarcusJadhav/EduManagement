package com.example.Trainer.Services;


import com.example.Trainer.DTO.TrainerRequest;
import com.example.Trainer.DTO.TrainerResponse;
import com.example.Trainer.Entities.Trainer;

import java.util.List;

public interface TrainerService {
    TrainerResponse createTrainer(TrainerRequest request);

    TrainerResponse addTrainer(TrainerRequest request);

    List<TrainerResponse> getAllTrainers();

    TrainerResponse getTrainerById(Long id);

    void deleteTrainer(Long id);

    TrainerResponse updateTrainer(Long id,TrainerRequest request);

}
