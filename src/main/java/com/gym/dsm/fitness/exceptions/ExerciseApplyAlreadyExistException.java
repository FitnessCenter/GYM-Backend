package com.gym.dsm.fitness.exceptions;

import com.gym.dsm.fitness.errors.BusinessException;
import com.gym.dsm.fitness.errors.ErrorCode;

public class ExerciseApplyAlreadyExistException extends BusinessException {
    public ExerciseApplyAlreadyExistException() {
        super(ErrorCode.EXERCISE_APPLY_ALREADY_EXISTS);
    }
}
