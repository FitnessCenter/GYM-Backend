package com.gym.dsm.fitness.services;

import com.gym.dsm.fitness.payloads.requests.EquipmentApplyRequest;
import com.gym.dsm.fitness.payloads.responses.EquipmentApplyResponse;

import java.util.List;

public interface EquipmentApplyService {
    List<EquipmentApplyResponse> getEquipmentApplies();
    List<EquipmentApplyResponse> getEquipmentAppliesList(String whose);
    void createEquipmentApply(EquipmentApplyRequest request);
    void updateEquipmentApply(Integer id, EquipmentApplyRequest request);
    void deleteEquipmentApply(Integer id);
}
