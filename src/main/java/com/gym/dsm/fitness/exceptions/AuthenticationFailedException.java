package com.gym.dsm.fitness.exceptions;

import com.gym.dsm.fitness.errors.BusinessException;
import com.gym.dsm.fitness.errors.ErrorCode;

public class AuthenticationFailedException extends BusinessException {
    public AuthenticationFailedException() { super(ErrorCode.TOKEN_AUTHENTICATION_FAILED); }
}
