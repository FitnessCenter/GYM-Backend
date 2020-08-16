package com.gym.dsm.fitness.exceptions;

import com.gym.dsm.fitness.errors.BusinessException;
import com.gym.dsm.fitness.errors.ErrorCode;

public class PasswordNotMatchException extends BusinessException {
    public PasswordNotMatchException() { super(ErrorCode.PASSWORD_NOT_MATCH); }
}
