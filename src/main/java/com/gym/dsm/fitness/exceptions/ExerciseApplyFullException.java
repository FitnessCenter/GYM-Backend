package com.gym.dsm.fitness.exceptions;

import com.gym.dsm.fitness.errors.BusinessException;
import com.gym.dsm.fitness.errors.ErrorCode;


public class ExerciseApplyFullException extends BusinessException {
    public ExerciseApplyFullException() {
        super(ErrorCode.EXERCISE_APPLY_FULL);
    }
}
