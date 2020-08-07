package com.gym.dsm.fitness.services;

import com.gym.dsm.fitness.payloads.requests.CreateAccountRequest;
import com.gym.dsm.fitness.payloads.responses.CreateAccountResponse;

public interface AccountService {
    public void getAccount();
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);
    public void updatePassword();
}
