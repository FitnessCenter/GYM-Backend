package com.gym.dsm.fitness.exceptions;

import com.gym.dsm.fitness.errors.BusinessException;
import com.gym.dsm.fitness.errors.ErrorCode;

public class ForbiddenTimeException extends BusinessException {
    public ForbiddenTimeException() { super(ErrorCode.FORBIDDEN_TIME); }
}