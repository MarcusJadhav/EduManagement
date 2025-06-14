package com.example.Trainer.Services;

import com.example.Trainer.DTO.AuthenticationRequest;
import com.example.Trainer.DTO.AuthenticationResponse;
import com.example.Trainer.Entities.Trainer;
import com.example.Trainer.Repositories.TrainerRepository;
import com.example.Trainer.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainerAuthService {

    private final TrainerRepository trainerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(AuthenticationRequest request) {
        Trainer trainer = Trainer.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_TRAINER")
                .build();

        trainerRepository.save(trainer);
        String token = jwtService.generateToken(trainer);
        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email or password");
        }

        Trainer trainer = trainerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        String token = jwtService.generateToken(trainer);
        return AuthenticationResponse.builder().token(token).build();
    }
}
