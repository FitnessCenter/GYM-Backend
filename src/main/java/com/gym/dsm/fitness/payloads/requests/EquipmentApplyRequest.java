package com.gym.dsm.fitness.payloads.requests;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EquipmentApplyRequest {
    private Integer numberOfApply;
    private Integer price;
    private String equipmentName;
    private String purchaseLink;
}