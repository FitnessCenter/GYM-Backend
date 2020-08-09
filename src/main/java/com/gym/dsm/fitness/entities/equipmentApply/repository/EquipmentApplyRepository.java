package com.gym.dsm.fitness.entities.equipmentApply.repository;

import com.gym.dsm.fitness.entities.equipmentApply.EquipmentApply;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EquipmentApplyRepository extends CrudRepository<EquipmentApply, Integer> {
    EquipmentApply save(EquipmentApply equipmentApply);
    List<EquipmentApply> findAll();
    List<EquipmentApply> findByAppliedUserId(String applyUserId);
    Optional<EquipmentApply> findById(Integer id);
}
