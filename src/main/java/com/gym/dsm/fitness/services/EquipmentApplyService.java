package com.gym.dsm.fitness.services;

import com.gym.dsm.fitness.payloads.request.EquipmentApplyRequest;
import com.gym.dsm.fitness.payloads.request.EquipmentApplyResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EquipmentApplyService {
    List<EquipmentApplyResponse> getEquipmentApplies();
    void createEquipmentApply(EquipmentApplyRequest request);
}
