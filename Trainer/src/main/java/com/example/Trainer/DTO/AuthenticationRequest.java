package com.example.Trainer.DTO;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
