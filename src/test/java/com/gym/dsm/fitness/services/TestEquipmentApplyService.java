package com.gym.dsm.fitness.services;

import com.gym.dsm.fitness.config.ModelMapperConfiguration;
import com.gym.dsm.fitness.entities.equipmentApply.EquipmentApply;
import com.gym.dsm.fitness.entities.equipmentApply.repository.EquipmentApplyRepository;
import com.gym.dsm.fitness.payloads.requests.EquipmentApplyRequest;
import com.gym.dsm.fitness.payloads.responses.EquipmentApplyResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {EquipmentApplyServiceImp.class, ModelMapperConfiguration.class})
public class TestEquipmentApplyService {

    @MockBean
    EquipmentApplyRepository equipmentApplyRepository;

    @Autowired
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

        List<EquipmentApplyResponse> gotEquipmentApplies = equipmentApplyService.getEquipmentApplies();
        assertThat(gotEquipmentApplies.get(0).getId(), is(equipmentApply.getId()));
    }
}