package com.gym.dsm.fitness.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(400, "Bad Request"),
    USER_ALREADY_EXISTS(409, "User alraedy exists");

    private final int status;

    private final String message;
}
