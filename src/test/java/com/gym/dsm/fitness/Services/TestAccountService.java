package com.gym.dsm.fitness.Services;

import com.gym.dsm.fitness.entities.user.EquipmentApply;
import com.gym.dsm.fitness.services.EquipmentApplyService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TestAccountService {

    @Mock
    EquipmentApplyRepository equipmentApplyRepository;

    @InjectMocks
    EquipmentApplyService equipmentApplyService;
}
