package com.gym.dsm.fitness.controllers;

import com.gym.dsm.fitness.entities.equipmentApply.EquipmentApply;
import com.gym.dsm.fitness.payloads.requests.EquipmentApplyRequest;
import com.gym.dsm.fitness.payloads.responses.EquipmentApplyResponse;
import com.gym.dsm.fitness.services.EquipmentApply.EquipmentApplyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/equipment-applies")
@RequiredArgsConstructor
public class EquipmentApplyController {

    private final EquipmentApplyService equipmentApplyService;

    private final ModelMapper modelMapper;

    private EquipmentApplyResponse entityToResponse(EquipmentApply equipmentApply){
        EquipmentApplyResponse equipmentApplyResponse = modelMapper.map(equipmentApply, EquipmentApplyResponse.class);
        return equipmentApplyResponse;
    }

    @GetMapping
    public List<EquipmentApplyResponse> getEquipmentApplies() {
        return equipmentApplyService.getEquipmentApplies().stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createEquipmentApply(@RequestBody @Valid EquipmentApplyRequest request) {
        equipmentApplyService.createEquipmentApply(request);
    }

    @PutMapping("/{id}")
    public void updateEquipmentApply(@PathVariable Integer id) {
        equipmentApplyService.updateEquipmentApply(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipmentApply(@PathVariable Integer id) {
        equipmentApplyService.deleteEquipmentApply(id);
    }

}
