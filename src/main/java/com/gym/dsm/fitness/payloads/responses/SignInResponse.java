package com.gym.dsm.fitness.payloads.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInResponse {
    private String accessToken;

    private String refreshToken;
}
