package com.gym.dsm.fitness.controllers;

import com.gym.dsm.fitness.payloads.requests.EquipmentApplyRequest;
import com.gym.dsm.fitness.payloads.responses.EquipmentApplyResponse;
import com.gym.dsm.fitness.services.EquipmentApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/equipment-applies")
@RequiredArgsConstructor
public class EquipmentApplyController {
    private final EquipmentApplyService equipmentApplyService;

    @GetMapping
    public List<EquipmentApplyResponse> getEquipmentApplies() {
        return equipmentApplyService.getEquipmentApplies();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createEquipmentApply(@RequestBody @Valid EquipmentApplyRequest request) {
        equipmentApplyService.createEquipmentApply(request);
    }

    @PutMapping("/{id}")
    public void updateEquipmentApply(@PathVariable Integer id, @RequestBody @Valid EquipmentApplyRequest request) {
        equipmentApplyService.updateEquipmentApply(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipmentApply(@PathVariable Integer id) {
        equipmentApplyService.deleteEquipmentApply(id);
    }

}
