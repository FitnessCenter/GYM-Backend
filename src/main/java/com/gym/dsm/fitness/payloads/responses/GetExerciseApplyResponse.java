package com.gym.dsm.fitness.payloads.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetExerciseApplyResponse {
    private Integer time;

    private Integer numberOfAppliedUser;

    private Integer userLimit;

    private Boolean isApplied;
}
