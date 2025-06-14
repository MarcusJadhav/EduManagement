package com.example.Trainer.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainerResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String expertise;
}
