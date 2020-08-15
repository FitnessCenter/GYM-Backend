package com.gym.dsm.fitness.payloads.requests;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class UpdatePasswordRequest {
    @NotEmpty
    private String currentPassword;

    @NotEmpty
    private String newPassword;
}
