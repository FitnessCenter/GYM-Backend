package com.gym.dsm.fitness.services.Auth;

import com.gym.dsm.fitness.payloads.requests.SignInRequest;
import com.gym.dsm.fitness.payloads.responses.SignInResponse;

public interface AuthService {
    public SignInResponse signIn(SignInRequest signInRequest);
}
