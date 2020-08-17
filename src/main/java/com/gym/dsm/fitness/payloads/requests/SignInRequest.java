package com.gym.dsm.fitness.payloads.requests;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInRequest {
    private String id;

    private String password;
}
