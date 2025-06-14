package com.example.Trainer.Repositories;

import com.example.Trainer.Entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer,Long>
{
    Optional<Trainer> findByEmail(String email);
}
