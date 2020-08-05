package com.gym.dsm.fitness.controllers;

import com.gym.dsm.fitness.payloads.request.CreateAccountRequest;
import com.gym.dsm.fitness.payloads.response.CreateAccountResponse;
import com.gym.dsm.fitness.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public CreateAccountResponse createAccount(@RequestBody @Valid CreateAccountRequest createAccountRequest) {
        return accountService.createAccount(createAccountRequest);
    }
}