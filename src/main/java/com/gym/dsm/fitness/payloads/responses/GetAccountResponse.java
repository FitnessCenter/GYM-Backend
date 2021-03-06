package com.gym.dsm.fitness.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class GetAccountResponse {
    private String StudentName;

    private String studentNumber;
}
