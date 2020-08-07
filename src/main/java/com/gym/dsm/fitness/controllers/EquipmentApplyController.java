package com.gym.dsm.fitness.controllers;

import com.gym.dsm.fitness.payloads.request.EquipmentApplyRequest;
import com.gym.dsm.fitness.payloads.response.EquipmentApplyResponse;
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
    public List<EquipmentApplyResponse> getEquipmentApplies(){
        return equipmentApplyService.getEquipmentApplies();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createEquipmentApply(@RequestBody @Valid EquipmentApplyRequest request){
        equipmentApplyService.createEquipmentApply(request);
    }

}
