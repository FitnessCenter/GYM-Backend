package com.gym.dsm.fitness.services;

import com.gym.dsm.fitness.config.ModelMapperConfiguration;
import com.gym.dsm.fitness.entities.equipmentApply.EquipmentApply;
import com.gym.dsm.fitness.entities.equipmentApply.repository.EquipmentApplyRepository;
import com.gym.dsm.fitness.entities.user.User;
import com.gym.dsm.fitness.entities.user.repository.UserRepository;
import com.gym.dsm.fitness.exceptions.AccessDeniedException;
import com.gym.dsm.fitness.exceptions.NotFoundException;
import com.gym.dsm.fitness.payloads.requests.EquipmentApplyRequest;
import com.gym.dsm.fitness.payloads.responses.EquipmentApplyResponse;
import com.gym.dsm.fitness.security.auth.AuthenticationFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {EquipmentApplyServiceImp.class, ModelMapperConfiguration.class})
public class TestEquipmentApplyService {

    @MockBean
    EquipmentApplyRepository equipmentApplyRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    AuthenticationFacade authenticationFacade;

    @Autowired
    EquipmentApplyServiceImp equipmentApplyService;

    private final User user = User
            .builder()
            .id("user")
            .build();

    private final EquipmentApplyRequest request = EquipmentApplyRequest
            .builder()
            .numberOfApply(12)
            .price(3000)
            .equipmentName("12kg 덤벨")
            .purchaseLink("http://bitly.kr/ZUuEfi7UgVx")
            .build();

    private final EquipmentApplyResponse response = EquipmentApplyResponse
            .builder()
            .id(1)
            .numberOfApply(12)
            .price(3000)
            .equipmentName("12kg 덤벨")
            .purchaseLink("http://bitly.kr/ZUuEfi7UgVx")
            .build();

    private final EquipmentApply entity = EquipmentApply
            .builder()
            .id(1)
            .numberOfApply(12)
            .price(3000)
            .equipmentName("12kg 덤벨")
            .purchaseLink("http://bitly.kr/ZUuEfi7UgVx")
            .appliedUser(user)
            .build();



    @Test
    public void expectNothingWhenCreateEquipmentApplyCorrectly(){
        when(authenticationFacade.getUserId()).thenReturn(this.user.getId());
        when(userRepository.findById(this.user.getId())).thenReturn(Optional.of(this.user));

        equipmentApplyService.createEquipmentApply(this.request);
    }



    @Test
    public void expectToReturnEquipmentApplyWhenGetEquipmentApplyCorrectly(){
        ArrayList<EquipmentApply> equipmentApplies = new ArrayList<>();
        equipmentApplies.add(this.entity);
        when(equipmentApplyRepository.findAll()).thenReturn(equipmentApplies);

        List<EquipmentApplyResponse> gotEquipmentApplies = equipmentApplyService.getEquipmentApplies();
        assertThat(gotEquipmentApplies.get(0).getId(), is(entity.getId()));
    }



    @Test
    public void expectNothingWhenUpdateEquipmentApplyCorrectly(){
        when(userRepository.findById(this.user.getId())).thenReturn(Optional.of(this.user));
        when(equipmentApplyRepository.findById(this.entity.getId())).thenReturn(Optional.of(this.entity));
        when(authenticationFacade.getUserId()).thenReturn(this.user.getId());

        equipmentApplyService.updateEquipmentApply(this.entity.getId(), this.request);
    }



    @Test
    public void expectNothingWhenDeleteEquipmentApplyCorrectly(){
        when(userRepository.findById(this.user.getId())).thenReturn(Optional.of(this.user));
        when(equipmentApplyRepository.findById(this.entity.getId())).thenReturn(Optional.of(this.entity));
        when(authenticationFacade.getUserId()).thenReturn(this.user.getId());

        equipmentApplyService.deleteEquipmentApply(this.entity.getId());
    }


    @Test
    public void expectAccessDeniedExceptionWhenVerifyAccessWithoutAuthority(){
        EquipmentApply equipmentApply = this.entity;
        equipmentApply.setAppliedUser(User.builder().id("test_2").build());

        when(userRepository.findById(this.user.getId())).thenReturn(Optional.of(this.user));
        when(equipmentApplyRepository.findById(this.entity.getId())).thenReturn(Optional.of(equipmentApply));
        when(authenticationFacade.getUserId()).thenReturn(this.user.getId());

        try {
            equipmentApplyService.verifyAccessAuthority(this.entity);
        } catch (AccessDeniedException e){
            assertThat(e.getMessage(), is("Access denied"));
        }
    }


    @Test
    public void expectNotFoundExceptionWhenFindNotExistEquipmentApply(){
        when(equipmentApplyRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));

        try{
            equipmentApplyService.findEquipmentApplyById(1);
        }catch (NotFoundException e){
            assertThat(e.getMessage(), is("Not found"));
        }
    }


}