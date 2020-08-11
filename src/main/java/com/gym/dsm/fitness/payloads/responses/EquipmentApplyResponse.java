package com.gym.dsm.fitness.payloads.responses;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentApplyResponse {

    @NotEmpty
    private Integer id;

    @NotEmpty
    private Integer numberOfApply;

    @NotEmpty
    private Integer price;

    @NotBlank
    @NotEmpty
    private String equipmentName;

    @NotBlank
    @NotEmpty
    private String purchaseLink;
}
