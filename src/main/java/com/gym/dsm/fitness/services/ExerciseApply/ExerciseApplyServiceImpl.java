package com.gym.dsm.fitness.services.ExerciseApply;


import com.gym.dsm.fitness.entities.exerciseApply.ExerciseApply;
import com.gym.dsm.fitness.entities.exerciseApply.repository.ExerciseApplyRepository;
import com.gym.dsm.fitness.entities.user.User;
import com.gym.dsm.fitness.entities.user.repository.UserRepository;
import com.gym.dsm.fitness.exceptions.*;
import com.gym.dsm.fitness.payloads.responses.GetAccountResponse;
import com.gym.dsm.fitness.payloads.responses.GetExerciseApplyOfUserResponse;
import com.gym.dsm.fitness.payloads.responses.GetExerciseApplyResponse;
import com.gym.dsm.fitness.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseApplyServiceImpl implements ExerciseApplyService{

    private final ExerciseApplyRepository exerciseApplyRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;
    private final ModelMapper modelMapper;
    Integer exerciseAppliesInitDay = null;

    @Value("${service.apply-start-time}")
    Integer applyStartTime;

    @Value("${service.apply-close-time}")
    Integer applyCloseTime;

    @Value("${service.number-of-exercise-time}")
    Integer numberOfTime;

    @Value("${service.limit-of-exercise-applies}")
    Integer limitOfExerciseApply;


    @Override
    public List<GetExerciseApplyResponse> getExerciseApplies(){
        User user = userRepository.findById(authenticationFacade.getUserId())
                .orElseThrow(AuthenticationFailedException::new);

        initExerciseApply();

        List<GetExerciseApplyResponse> appliesPerTime = new ArrayList<>();
        List<ExerciseApply> applies = exerciseApplyRepository.findAll();

        for(int i = 0; i < numberOfTime; i++){
            Boolean isApplied = false;
            Integer numberOfAppliedUser = 0;

            for(int j = 0; j < applies.size(); j++){
                ExerciseApply apply = applies.get(j);

                if(apply.getExerciseTime() == i){
                    numberOfAppliedUser += 1;
                }
                if(apply.getAppliedUser() == user){
                    isApplied = true;
                }

            }

            appliesPerTime.add(
                    GetExerciseApplyResponse
                    .builder()
                    .time(i)
                    .isApplied(isApplied)
                    .numberOfAppliedUser(numberOfAppliedUser)
                    .userLimit(limitOfExerciseApply)
                    .build()
            );
        }

        return appliesPerTime;
    }


    @Override
    public List<GetAccountResponse> getExerciseAppliersByTime(Integer time){
        initExerciseApply();
        validateTime(time);

        List<ExerciseApply> applies = exerciseApplyRepository.findByExerciseTime(time);

        return applies.stream()
                .map((e) -> modelMapper.map(e.getAppliedUser(), GetAccountResponse.class))
                .collect(Collectors.toList());
    }


    @Override
    public void applyExercise(Integer time){
        User user = userRepository.findById(authenticationFacade.getUserId())
                .orElseThrow(AuthenticationFailedException::new);

        initExerciseApply();
        validateTime(time);
        checkNowIsApplyTime();
        checkApplyLimitOfTime(time);

        checkUserApplySeveralTimes(user);

        ExerciseApply apply = ExerciseApply
                .builder()
                .appliedUser(user)
                .exerciseTime(time)
                .build();

        exerciseApplyRepository.save(apply);
    }


    @Override
    public void deleteExerciseApplyOfUser() {
        User user = userRepository.findById(authenticationFacade.getUserId())
                .orElseThrow(AuthenticationFailedException::new);

        initExerciseApply();

        ExerciseApply exerciseApply = user.getExerciseApply();

        if(exerciseApply == null){
            throw new NotFoundException();
        }
        else {
            exerciseApplyRepository.deleteById(exerciseApply.getId());
        }
    }


    @Override
    public GetExerciseApplyOfUserResponse getExerciseApplyOfUser() {
        User user = userRepository.findById(authenticationFacade.getUserId())
                .orElseThrow(AuthenticationFailedException::new);

        initExerciseApply();

        ExerciseApply exerciseApply = user.getExerciseApply();

        if(exerciseApply == null){
            throw new NotFoundException();
        }
        return modelMapper.map(exerciseApply, GetExerciseApplyOfUserResponse.class);
    }


    void validateTime(Integer time) {
        if (time < 0 && time >= numberOfTime) {
            throw new BadRequestException();
        }
    }


    void checkUserApplySeveralTimes(User user){
        if (user.getExerciseApply() != null){
            throw new ExerciseApplyAlreadyExistException();
        }
    }


    void checkApplyLimitOfTime(Integer time){
        List<ExerciseApply> exerciseApplies = exerciseApplyRepository.findByExerciseTime(time);
        if(exerciseApplies.size() >= limitOfExerciseApply){
            throw new ExerciseApplyFullException();
        }
    }


    void initExerciseApply(){
        Calendar cal = Calendar.getInstance();
        Integer currentDay = cal.get(Calendar.DAY_OF_MONTH);

        if(exerciseAppliesInitDay == null || exerciseAppliesInitDay != currentDay){
            exerciseAppliesInitDay = currentDay;
            exerciseApplyRepository.deleteAll();
        }
    }

    void checkNowIsApplyTime(){
        Calendar cal = Calendar.getInstance();
        Integer currentHourAndMinute = cal.get(Calendar.HOUR_OF_DAY)*100 + cal.get(Calendar.MINUTE);

        if(currentHourAndMinute < applyStartTime || currentHourAndMinute > applyCloseTime){
            throw new ForbiddenTimeException();
        }
    }

}
