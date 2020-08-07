package com.gym.dsm.fitness.payloads.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateAccountResponse {
    private String message;
}
