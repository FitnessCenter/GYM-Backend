package com.gym.dsm.fitness.entities.equipmentApply.repository;

import com.gym.dsm.fitness.entities.equipmentApply.EquipmentApply;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentApplyRepository extends CrudRepository<EquipmentApply, Integer> {
    EquipmentApply save(EquipmentApply equipmentApply);
    List<EquipmentApply> findAll();
    List<EquipmentApply> findByAppliedUserId(String applyUserId);
    List<EquipmentApply> findBySex(Boolean sex);
    Optional<EquipmentApply> findById(Integer id);

    @Override
    void deleteById(Integer integer);
}
