package com.gym.dsm.fitness.payloads.requests;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class CreateAccountRequest {

    @Max(4)
    @NotEmpty
    private String studentNumber;

    @NotEmpty
    private String studentName;

    @NotEmpty
    private String id;

    @NotEmpty
    private String password;

    private Boolean sex;
}
