package com.gym.dsm.fitness.entities.exerciseApply.repository;

import com.gym.dsm.fitness.entities.exerciseApply.ExerciseApply;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExerciseApplyRepository extends CrudRepository<ExerciseApply, Integer> {
    ExerciseApply save(ExerciseApply exerciseApply);
    List<ExerciseApply> findAll();
    List<ExerciseApply> findByExerciseTime(Integer time);

    @Override
    void deleteById(Integer integer);
    @Override
    void delete(ExerciseApply entity);
}
