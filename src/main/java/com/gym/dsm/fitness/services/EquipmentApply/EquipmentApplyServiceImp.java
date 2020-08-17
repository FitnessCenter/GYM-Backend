package com.gym.dsm.fitness.services.EquipmentApply;

import com.gym.dsm.fitness.entities.equipmentApply.EquipmentApply;
import com.gym.dsm.fitness.entities.equipmentApply.repository.EquipmentApplyRepository;
import com.gym.dsm.fitness.payloads.requests.EquipmentApplyRequest;
import com.gym.dsm.fitness.services.EquipmentApply.EquipmentApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EquipmentApplyServiceImp implements EquipmentApplyService {
    private final EquipmentApplyRepository equipmentApplyRepository;

    @Override
    public List<EquipmentApply> getEquipmentApplies() {
        List<EquipmentApply> equipmentApplies = equipmentApplyRepository.findAll();
        return equipmentApplies;
    }

    @Override
    public void createEquipmentApply(EquipmentApplyRequest request) {
//        EquipmentApply new_apply = EquipmentApply.builder()
//                .numberOfApply(request.getNumberOfApply())
//                .equipmentName(request.getEquipmentName())
//                .purchaseLink(request.getPurchaseLink())
//                .price(request.getPrice())
//                .appliedUser(null)
//                .build();
//
//        EquipmentApply createdApply = equipmentApplyRepository.save(new_apply);

    }

    @Override
    public void updateEquipmentApply(Integer id) {

    }

    @Override
    public void deleteEquipmentApply(Integer id) {
    }
}
