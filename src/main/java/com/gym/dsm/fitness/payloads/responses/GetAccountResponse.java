package com.gym.dsm.fitness.payloads.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetAccountResponse {
    private String StudentName;

    private String studentNumber;
}
