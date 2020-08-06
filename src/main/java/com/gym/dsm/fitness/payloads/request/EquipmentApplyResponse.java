package com.gym.dsm.fitness.payloads.request;



import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
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
