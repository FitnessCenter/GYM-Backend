package com.gym.dsm.fitness.services;

import com.gym.dsm.fitness.entities.user.User;
import com.gym.dsm.fitness.entities.user.repository.UserRepository;
import com.gym.dsm.fitness.exceptions.AuthenticationFailedException;
import com.gym.dsm.fitness.exceptions.UserAlreadyExistsException;
import com.gym.dsm.fitness.payloads.requests.CreateAccountRequest;
import com.gym.dsm.fitness.payloads.responses.GetAccountResponse;
import com.gym.dsm.fitness.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;

    private final AuthenticationFacade authenticationFacade;

    private final PasswordEncoder passwordEncoder;

    @Override
    public GetAccountResponse getAccount() {
        User user = userRepository.findById(authenticationFacade.getUserId())
                .orElseThrow(AuthenticationFailedException::new);

        return GetAccountResponse.builder()
                .StudentName(user.getStudentName())
                .studentNumber(user.getStudentNumber())
                .build();
    }

    @Override
    public void createAccount(CreateAccountRequest createAccountRequest) {
        userRepository.findById(createAccountRequest.getId()).ifPresent(user -> {
            throw new UserAlreadyExistsException();
        });

        userRepository.findByStudentNumber(createAccountRequest.getStudentNumber()).ifPresent(user -> {
            throw new UserAlreadyExistsException();
        });

        userRepository.save(
                User.builder()
                        .id(createAccountRequest.getId())
                        .password(passwordEncoder.encode(createAccountRequest.getPassword()))
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
