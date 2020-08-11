package com.gym.dsm.fitness.exceptions;

import com.gym.dsm.fitness.errors.BusinessException;
import com.gym.dsm.fitness.errors.ErrorCode;

public class BadRequestException extends BusinessException {
    BadRequestException() { super(ErrorCode.BAD_REQUEST); }
}
