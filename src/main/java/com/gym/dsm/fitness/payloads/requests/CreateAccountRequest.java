package com.gym.dsm.fitness.payloads.requests;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Builder
public class CreateAccountRequest {

    @Size(min = 4, max = 4)
    private String studentNumber;

    @NotEmpty
    private String studentName;

    @NotEmpty
    private String id;

    @NotEmpty
    private String password;

    private Boolean sex;
}
