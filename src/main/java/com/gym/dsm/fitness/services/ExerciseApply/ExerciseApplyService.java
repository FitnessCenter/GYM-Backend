package com.gym.dsm.fitness.services.ExerciseApply;

import com.gym.dsm.fitness.payloads.responses.GetAccountResponse;
import com.gym.dsm.fitness.payloads.responses.GetExerciseApplyOfUserResponse;
import com.gym.dsm.fitness.payloads.responses.GetExerciseApplyResponse;

import java.util.List;

public interface ExerciseApplyService {
    List<GetExerciseApplyResponse> getExerciseApplies();
    List<GetAccountResponse> getExerciseAppliersByTime(Integer time);
    void applyExercise(Integer time);
    void deleteExerciseApplyOfUser();
    GetExerciseApplyOfUserResponse getExerciseApplyOfUser();
}
