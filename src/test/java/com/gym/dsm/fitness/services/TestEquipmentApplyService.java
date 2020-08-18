package com.gym.dsm.fitness.services;

import com.gym.dsm.fitness.entities.equipmentApply.EquipmentApply;
import com.gym.dsm.fitness.entities.equipmentApply.repository.EquipmentApplyRepository;
import com.gym.dsm.fitness.payloads.requests.EquipmentApplyRequest;
import com.gym.dsm.fitness.services.EquipmentApply.EquipmentApplyServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
public class TestEquipmentApplyService {

    @Mock
    EquipmentApplyRepository equipmentApplyRepository;

    @InjectMocks
    EquipmentApplyServiceImp equipmentApplyService;

    private final EquipmentApplyRequest request = EquipmentApplyRequest.builder()
            .build();

    private final EquipmentApply equipmentApply = EquipmentApply.builder()
            .id(1)
            .build();



    @Test
    public void when_get_equipment_apply_it_should_return_equipment_apply_list(){
        ArrayList<EquipmentApply> equipmentApplies = new ArrayList<>();
        equipmentApplies.add(this.equipmentApply);
        when(equipmentApplyRepository.findAll()).thenReturn(equipmentApplies);

        List<EquipmentApply> gotEquipmentApplies = equipmentApplyService.getEquipmentApplies();
        assertThat(gotEquipmentApplies.get(0).getId(), is(equipmentApply.getId()));
    }
}