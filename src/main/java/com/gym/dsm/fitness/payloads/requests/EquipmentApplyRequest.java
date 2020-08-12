package com.gym.dsm.fitness.payloads.requests;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EquipmentApplyRequest {
    private Integer numberOfApply;
    private Integer price;
    private String equipmentName;
    private String purchaseLink;
}