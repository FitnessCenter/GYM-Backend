package com.gym.dsm.fitness.exceptions;

import com.gym.dsm.fitness.errors.BusinessException;
import com.gym.dsm.fitness.errors.ErrorCode;

public class NotFoundException extends BusinessException {
    public NotFoundException() { super(ErrorCode.NOT_FOUND); }
}
