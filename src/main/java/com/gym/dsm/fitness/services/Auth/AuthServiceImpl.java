package com.gym.dsm.fitness.services.Auth;

import com.gym.dsm.fitness.entities.user.User;
import com.gym.dsm.fitness.entities.user.repository.UserRepository;
import com.gym.dsm.fitness.exceptions.AuthenticationFailedException;
import com.gym.dsm.fitness.payloads.requests.SignInRequest;
import com.gym.dsm.fitness.payloads.responses.SignInResponse;
import com.gym.dsm.fitness.security.JWTProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTProvider jwtProvider;

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        userRepository.findById(signInRequest.getId())
                .filter(user -> passwordEncoder.matches(signInRequest.getPassword(), user.getPassword()))
                .orElseThrow(AuthenticationFailedException::new);

        String accessToken = jwtProvider.generateAccessToken(signInRequest.getId());
        String refreshToken = jwtProvider.generateRefreshToken(signInRequest.getId());

        return SignInResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
