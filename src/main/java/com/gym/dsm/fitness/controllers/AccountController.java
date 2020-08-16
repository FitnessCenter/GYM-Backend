package com.gym.dsm.fitness.controllers;

import com.gym.dsm.fitness.payloads.requests.CreateAccountRequest;
import com.gym.dsm.fitness.payloads.requests.UpdatePasswordRequest;
import com.gym.dsm.fitness.payloads.responses.GetAccountResponse;
import com.gym.dsm.fitness.services.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public GetAccountResponse getAccount() {
        return accountService.getAccount();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createAccount(@RequestBody @Valid CreateAccountRequest createAccountRequest) {
        accountService.createAccount(createAccountRequest);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest updatePasswordRequest) {
        accountService.updatePassword(updatePasswordRequest);
    }
}