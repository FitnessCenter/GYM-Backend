package com.gym.dsm.fitness.controllers;

import com.gym.dsm.fitness.payloads.responses.GetAccountResponse;
import com.gym.dsm.fitness.payloads.responses.GetExerciseApplyOfUserResponse;
import com.gym.dsm.fitness.payloads.responses.GetExerciseApplyResponse;
import com.gym.dsm.fitness.payloads.responses.GetNumberOfDaysExercisedOfUserResponse;
import com.gym.dsm.fitness.services.ExerciseApply.ExerciseApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/exercise-applies")
@RequiredArgsConstructor
public class ExerciseApplyController {
    private final ExerciseApplyService exerciseApplyService;


    @GetMapping
    public List<GetExerciseApplyResponse> getExerciseApplies(){
        return exerciseApplyService.getExerciseApplies();
    }

    @GetMapping("/{time}/appliers")
    public List<GetAccountResponse> getExerciseApplyAppliers(@PathVariable Integer time){
        return exerciseApplyService.getExerciseAppliersByTime(time);
    }

    @GetMapping("/my")
    public GetExerciseApplyOfUserResponse getExerciseApplyOfUser(){
        return exerciseApplyService.getExerciseApplyOfUser();
    }

    @PostMapping("/{time}/apply")
    public void applyExercise(@PathVariable Integer time){
        exerciseApplyService.applyExercise(time);
    }

    @DeleteMapping("/my")
    public void deleteExerciseApplyOfUser(){
        exerciseApplyService.deleteExerciseApplyOfUser();
    }

    @GetMapping("/my/number-of-days-exercised")
    public GetNumberOfDaysExercisedOfUserResponse getNumberOfDaysExercisedOfUser() {
        return exerciseApplyService.getNumberOfDaysExercisedOfUser();
    }

}
