package com.gym.dsm.fitness.exceptions;

import com.gym.dsm.fitness.errors.BusinessException;
import com.gym.dsm.fitness.errors.ErrorCode;

public class AccessDeniedException extends BusinessException {
    public AccessDeniedException() { super(ErrorCode.ACCESS_DENIED); }
}
