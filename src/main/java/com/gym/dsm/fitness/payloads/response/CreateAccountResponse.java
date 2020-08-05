package com.gym.dsm.fitness.payloads.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateAccountResponse {
    private String message;
}
