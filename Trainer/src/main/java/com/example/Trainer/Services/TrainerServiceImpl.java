package com.example.Trainer.Services;

import com.example.Trainer.DTO.TrainerRequest;
import com.example.Trainer.DTO.TrainerResponse;
import com.example.Trainer.Entities.Trainer;
import com.example.Trainer.Exceptions.ResourceNotFoundException;
import com.example.Trainer.Repositories.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService
{
    private final TrainerRepository repository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public TrainerResponse createTrainer(TrainerRequest request) {
        Trainer trainer = Trainer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .expertise(request.getExpertise())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_TRAINER")
                .build();

        repository.save(trainer);
        return toResponse(trainer);
    }


    public TrainerResponse addTrainer(TrainerRequest request){
        Trainer trainer = Trainer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .expertise(request.getExpertise())
                .build();

        Trainer saved = repository.save(trainer);
        return toResponse(saved);
    }

    @Override
    public List<TrainerResponse> getAllTrainers() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public TrainerResponse getTrainerById(Long id) {
        Trainer trainer = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Trainer not found"));
        return toResponse(trainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        repository.deleteById(id);
    }

    @Override
    public TrainerResponse updateTrainer(Long id, TrainerRequest request) {
        Trainer trainer = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Trainer not found"));
        trainer.setName(request.getName());
        trainer.setEmail(request.getEmail());
        trainer.setPhone(request.getPhone());
        trainer.setExpertise(request.getExpertise());
        return toResponse(repository.save(trainer));
    }

    private TrainerResponse toResponse(Trainer trainer) {
        return TrainerResponse.builder()
                .id(trainer.getId())
                .name(trainer.getName())
                .email(trainer.getEmail())
                .phone(trainer.getPhone())
                .expertise(trainer.getExpertise())
                .build();

    }
}
