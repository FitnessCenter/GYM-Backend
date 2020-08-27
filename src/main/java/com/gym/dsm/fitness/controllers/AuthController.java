package com.gym.dsm.fitness.controllers;

import com.gym.dsm.fitness.payloads.requests.SignInRequest;
import com.gym.dsm.fitness.payloads.responses.SignInResponse;
import com.gym.dsm.fitness.services.Auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public SignInResponse signIn(@RequestBody @Valid SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }
}
