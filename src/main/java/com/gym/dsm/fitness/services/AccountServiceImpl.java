package com.gym.dsm.fitness.services;

import com.gym.dsm.fitness.entities.user.User;
import com.gym.dsm.fitness.entities.user.repository.UserRepository;
import com.gym.dsm.fitness.exceptions.UserAlreadyExistsException;
import com.gym.dsm.fitness.payloads.requests.CreateAccountRequest;
import com.gym.dsm.fitness.payloads.responses.CreateAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;

    @Override
    public void getAccount() {

    }

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        userRepository.findById(createAccountRequest.getId()).ifPresent(user -> {
            throw new UserAlreadyExistsException();
        });

        userRepository.findByStudentNumber(createAccountRequest.getStudentNumber()).ifPresent(user -> {
            throw new UserAlreadyExistsException();
        });

        userRepository.save(
                User.builder()
                        .id(createAccountRequest.getId())
                        .password(createAccountRequest.getPassword())
                        .studentName(createAccountRequest.getStudentName())
                        .studentNumber(createAccountRequest.getStudentNumber())
                        .sex(createAccountRequest.getSex())
                        .build()
        );

        return CreateAccountResponse.builder()
                .message("CREATED")
                .build();
    }

    @Override
    public void updatePassword() {

    }
}
