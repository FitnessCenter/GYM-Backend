package com.gym.dsm.fitness.services;

import com.gym.dsm.fitness.payloads.requests.EquipmentApplyRequest;
import com.gym.dsm.fitness.payloads.responses.EquipmentApplyResponse;
import com.gym.dsm.fitness.services.EquipmentApplyService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EquipmentApplyServiceImp implements EquipmentApplyService {

    @Override
    public List<EquipmentApplyResponse> getEquipmentApplies() {
        return null;
    }

    @Override
    public void createEquipmentApply(EquipmentApplyRequest request) {

    }

    @Override
    public void updateEquipmentApply(Integer id) {

    }

    @Override
    public void deleteEquipmentApply(Integer id) {
    }
}
