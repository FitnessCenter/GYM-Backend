package com.gym.dsm.fitness.services;

import com.gym.dsm.fitness.payloads.requests.CreateAccountRequest;
import com.gym.dsm.fitness.payloads.responses.CreateAccountResponse;
import com.gym.dsm.fitness.payloads.responses.GetAccountResponse;

public interface AccountService {
    public GetAccountResponse getAccount();
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);
    public void updatePassword();
}
