package com.gym.dsm.fitness.controllers;

import com.gym.dsm.fitness.payloads.requests.CreateAccountRequest;
import com.gym.dsm.fitness.payloads.responses.CreateAccountResponse;
import com.gym.dsm.fitness.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public CreateAccountResponse createAccount(@RequestBody @Valid CreateAccountRequest createAccountRequest) {
        return accountService.createAccount(createAccountRequest);
    }
}