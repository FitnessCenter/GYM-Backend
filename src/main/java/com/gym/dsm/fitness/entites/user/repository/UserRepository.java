package com.gym.dsm.fitness.entites.user.repository;

import com.gym.dsm.fitness.entites.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findById(String id);
    Optional<User> findByStudentNumber(String id);
}
