package com.gym.dsm.fitness.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.dsm.fitness.entities.equipmentApply.EquipmentApply;
import com.gym.dsm.fitness.entities.user.User;
import com.gym.dsm.fitness.payloads.requests.EquipmentApplyRequest;
import com.gym.dsm.fitness.payloads.responses.EquipmentApplyResponse;
import com.gym.dsm.fitness.security.JWTProvider;
import com.gym.dsm.fitness.services.EquipmentApply.EquipmentApplyService;
import com.gym.dsm.fitness.services.EquipmentApplyServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@AutoConfigureMockMvc(addFilters = false)
public class TestEquipmentApplyController {

    private final String url = "/equipment-applies";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final EquipmentApplyRequest request = EquipmentApplyRequest
            .builder()
            .numberOfApply(12)
            .price(3000)
            .equipmentName("12kg ??")
            .purchaseLink("http://bitly.kr/ZUuEfi7UgVx")
            .build();

    private final EquipmentApplyResponse response = EquipmentApplyResponse
            .builder()
            .id(1)
            .numberOfApply(12)
            .price(3000)
            .equipmentName("12kg ??")
            .purchaseLink("http://bitly.kr/ZUuEfi7UgVx")
            .build();

    private final EquipmentApply entity = EquipmentApply
            .builder()
            .id(1)
            .numberOfApply(12)
            .price(3000)
            .equipmentName("12kg ??")
            .purchaseLink("http://bitly.kr/ZUuEfi7UgVx")
            .appliedUser(User.builder().build())
            .build();


    @Autowired
    MockMvc mvc;

    @MockBean
    private EquipmentApplyServiceImp equipmentApplyService;

    @MockBean
    private JWTProvider jwtProvider;

    @Test
    public void testGetEquipmentApply() throws Exception{
        List<EquipmentApplyResponse> response = new ArrayList<>();
        response.add(this.response);
        when(equipmentApplyService.getEquipmentApplies()).thenReturn(response);

        mvc.perform(MockMvcRequestBuilders
                .get(url))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void expect200WhenGetEquipmentWithQueryPram() throws Exception{
        ResultActions action = mvc.perform(MockMvcRequestBuilders
                .get(url+"?whose=men"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateEquipmentApply() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test void testUpdateEquipmentApply() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .put(url+"/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteEquipmentApply() throws Exception{
        ResultActions action = mvc.perform(MockMvcRequestBuilders
                .delete(url+"/1"))
                .andExpect(status().isOk());

    }
}

