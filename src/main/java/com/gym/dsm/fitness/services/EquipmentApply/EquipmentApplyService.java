package com.gym.dsm.fitness.services.EquipmentApply;

import com.gym.dsm.fitness.entities.equipmentApply.EquipmentApply;
import com.gym.dsm.fitness.payloads.requests.EquipmentApplyRequest;

import java.util.List;

public interface EquipmentApplyService {
    List<EquipmentApply> getEquipmentApplies();
    void createEquipmentApply(EquipmentApplyRequest request);
    void updateEquipmentApply(Integer id);
    void deleteEquipmentApply(Integer id);
}
