package com.gym.dsm.fitness.controller;


import com.gym.dsm.fitness.controllers.EquipmentApplyController;
import com.gym.dsm.fitness.payloads.request.EquipmentApplyResponse;
import com.gym.dsm.fitness.services.EquipmentApplyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EquipmentApplyController.class)
public class TestEquipmentApplyController {

    private final String url = "/equipment-applies";

    @Autowired
    MockMvc mvc;

    @MockBean
    private EquipmentApplyService equipmentApplyService;

    @Test
    public void testGetEquipmentApply() throws Exception{
        List response = new ArrayList<EquipmentApplyResponse>();
        response.add(
                EquipmentApplyResponse
                .builder()
                .id(1)
                .numberOfApply(12)
                .price(3000)
                .equipmentName("12kg 덤벨")
                .purchaseLink("http://bitly.kr/ZUuEfi7UgVx")
                .build()
        );

        when(equipmentApplyService.getEquipmentApplies()).thenReturn(response);

        mvc.perform(MockMvcRequestBuilders
                .get(url))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateEquipmentApply() throws Exception{
        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                .post(url))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test void testUpdateEquipmentApply() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .put(url+"1"));

    }

    @Test
    public void testDeleteEquipmentApply() throws Exception{
        ResultActions action = mvc.perform(MockMvcRequestBuilders
                .delete(url))
                .andExpect(status().isOk());

    }

}

