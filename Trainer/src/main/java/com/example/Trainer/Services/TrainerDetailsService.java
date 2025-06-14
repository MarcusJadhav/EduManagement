package com.example.Trainer.Services;

import com.example.Trainer.Entities.Trainer;
import com.example.Trainer.Repositories.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class TrainerDetailsService implements UserDetailsService {

    private final TrainerRepository trainerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Trainer trainer = trainerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Trainer not found: " + email));

        return User.builder()
                .username(trainer.getEmail())
                .password(trainer.getPassword())
                .authorities(Collections.singletonList(() -> trainer.getRole()))
                .build();
    }
}
