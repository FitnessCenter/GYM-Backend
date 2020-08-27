package com.gym.dsm.fitness.services.Account;

import com.gym.dsm.fitness.entities.user.User;
import com.gym.dsm.fitness.entities.user.repository.UserRepository;
import com.gym.dsm.fitness.exceptions.AuthenticationFailedException;
import com.gym.dsm.fitness.exceptions.PasswordNotMatchException;
import com.gym.dsm.fitness.exceptions.UserAlreadyExistsException;
import com.gym.dsm.fitness.payloads.requests.CreateAccountRequest;
import com.gym.dsm.fitness.payloads.requests.UpdatePasswordRequest;
import com.gym.dsm.fitness.payloads.responses.GetAccountResponse;
import com.gym.dsm.fitness.security.auth.AuthenticationFacade;
import com.gym.dsm.fitness.services.Account.AccountService;
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
    }

    @Override
    public void updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        User user = userRepository.findById(authenticationFacade.getUserId())
                .orElseThrow(AuthenticationFailedException::new);

        if (passwordEncoder.matches(updatePasswordRequest.getCurrentPassword(), user.getPassword())) {
            userRepository.save(user.updatePassword(updatePasswordRequest.getNewPassword()));
        } else {
            throw new PasswordNotMatchException();
        }
    }
}
