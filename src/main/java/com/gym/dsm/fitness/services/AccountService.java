package com.gym.dsm.fitness.services;

import com.gym.dsm.fitness.payloads.request.CreateAccountRequest;
import com.gym.dsm.fitness.payloads.response.CreateAccountResponse;

public interface AccountService {
    public void getAccount();
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);
    public void updatePassword();
}
