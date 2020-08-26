package com.gym.dsm.fitness.services;

import com.gym.dsm.fitness.entities.equipmentApply.EquipmentApply;
import com.gym.dsm.fitness.entities.equipmentApply.repository.EquipmentApplyRepository;
import com.gym.dsm.fitness.entities.user.User;
import com.gym.dsm.fitness.entities.user.repository.UserRepository;
import com.gym.dsm.fitness.exceptions.AccessDeniedException;
import com.gym.dsm.fitness.exceptions.AuthenticationFailedException;
import com.gym.dsm.fitness.exceptions.NotFoundException;
import com.gym.dsm.fitness.payloads.requests.EquipmentApplyRequest;
import com.gym.dsm.fitness.payloads.responses.EquipmentApplyResponse;
import com.gym.dsm.fitness.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EquipmentApplyServiceImp implements EquipmentApplyService {
    private final EquipmentApplyRepository equipmentApplyRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;
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
        User user = userRepository.findById(authenticationFacade.getUserId())
                .orElseThrow(AuthenticationFailedException::new);

        EquipmentApply new_apply = EquipmentApply.builder()
                .numberOfApply(request.getNumberOfApply())
                .equipmentName(request.getEquipmentName())
                .purchaseLink(request.getPurchaseLink())
                .price(request.getPrice())
                .appliedUser(user)
                .build();

        EquipmentApply createdApply = equipmentApplyRepository.save(new_apply);
    }

    @Override
    public void updateEquipmentApply(Integer id, EquipmentApplyRequest request) {
        EquipmentApply equipmentApplyToUpdate = findEquipmentApplyById(id);
        verifyAccessAuthority(equipmentApplyToUpdate);

        equipmentApplyToUpdate.setEquipmentName(request.getEquipmentName());
        equipmentApplyToUpdate.setNumberOfApply(request.getNumberOfApply());
        equipmentApplyToUpdate.setPrice(request.getPrice());
        equipmentApplyToUpdate.setPurchaseLink(request.getPurchaseLink());

        equipmentApplyRepository.save(equipmentApplyToUpdate);
    }

    @Override
    public void deleteEquipmentApply(Integer id) {
        EquipmentApply equipmentApplyToDelete = findEquipmentApplyById(id);
        verifyAccessAuthority(equipmentApplyToDelete);

        equipmentApplyRepository.deleteById(equipmentApplyToDelete.getId());
    }



    public EquipmentApply findEquipmentApplyById(Integer id) {
        EquipmentApply equipmentApply= equipmentApplyRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        return equipmentApply;
    }

    public void verifyAccessAuthority(EquipmentApply equipmentApply){
        User user = userRepository.findById(authenticationFacade.getUserId())
                .orElseThrow(AuthenticationFailedException::new);

        if(user != equipmentApply.getAppliedUser()){
            throw new AccessDeniedException();
        }
    }

}

