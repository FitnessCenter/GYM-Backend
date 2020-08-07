package com.gym.dsm.fitness.services;

import com.gym.dsm.fitness.payloads.requests.EquipmentApplyRequest;
import com.gym.dsm.fitness.payloads.responses.EquipmentApplyResponse;

import java.util.List;

public interface EquipmentApplyService {
    List<EquipmentApplyResponse> getEquipmentApplies();
    void createEquipmentApply(EquipmentApplyRequest request);
    void updateEquipmentApply(Integer id);
    void deleteEquipmentApply(Integer id);
}
