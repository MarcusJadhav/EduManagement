package com.example.Trainer.Controllers;

import com.example.Trainer.DTO.AuthenticationRequest;
import com.example.Trainer.DTO.AuthenticationResponse;
import com.example.Trainer.Services.TrainerAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class TrainerAuthController {

    private final TrainerAuthService trainerAuthService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = trainerAuthService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = trainerAuthService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
