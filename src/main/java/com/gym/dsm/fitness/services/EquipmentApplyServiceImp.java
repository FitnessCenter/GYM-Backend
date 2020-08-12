package com.gym.dsm.fitness.services;

import com.gym.dsm.fitness.entities.equipmentApply.EquipmentApply;
import com.gym.dsm.fitness.entities.equipmentApply.repository.EquipmentApplyRepository;
import com.gym.dsm.fitness.payloads.requests.EquipmentApplyRequest;
import com.gym.dsm.fitness.payloads.responses.EquipmentApplyResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EquipmentApplyServiceImp implements EquipmentApplyService {
    private final EquipmentApplyRepository equipmentApplyRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<EquipmentApplyResponse> getEquipmentApplies() {
        List<EquipmentApply> equipmentApplies = equipmentApplyRepository.findAll();

        return equipmentApplies.stream()
                .map((e) -> modelMapper.map(e, EquipmentApplyResponse.class))
                .collect(Collectors.toList());
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
