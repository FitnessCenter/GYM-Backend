package com.gym.dsm.fitness.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.dsm.fitness.payloads.request.CreateAccountRequest;
import com.gym.dsm.fitness.security.JWTProvider;
import com.gym.dsm.fitness.services.AccountService;
import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class)
public class TestAccountController {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JWTProvider jwtProvider;

    @MockBean
    private AccountService accountService;

    @Test
    public void testGetAccount() throws Exception {
        ResultActions resultActions = requestFactory("GET");

        resultActions
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testCreateAccount() throws Exception {
        ResultActions resultActions = requestFactory("POST");

        resultActions
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void testUpdateAccount() throws Exception {
        ResultActions resultActions = requestFactory("PUT");

        resultActions
                .andExpect(status().isOk())
                .andDo(print());
    }

    private ResultActions requestFactory(String method) throws Exception{
        String url = "/account";
        switch (method) {
            case "GET":
                RequestBuilder getRequestBuilder = RequestBuilder.builder()
                        .requestBuilder(get(url))
                        .token(jwtProvider.generateAccessToken())
                        .build();

                return requestAccount(getRequestBuilder.getRequest());

            case "POST":
                CreateAccountRequest createAccountRequest = CreateAccountRequest.builder()
                        .id("eojindev")
                        .password("p@ssword")
                        .studentName("김어진")
                        .studentNumber("1101")
                        .sex(true)
                        .build();

                RequestBuilder postRequestBuilder = RequestBuilder.builder()
                        .requestBuilder(post(url))
                        .body(createAccountRequest)
                        .build();

                return requestAccount(postRequestBuilder.getRequest());

            case "PUT":
                UpdateAccountRequest updateAccountRequest = UpdateAccountRequest.builder()
                        .currentPassword("currentP@ssword")
                        .newPassword("p@ssword")
                        .build();

                RequestBuilder putRequestBuilder = RequestBuilder.builder()
                        .requestBuilder(put(url))
                        .token(jwtProvider.generateAccessToken())
                        .body(updateAccountRequest)
                        .build();

                return requestAccount(putRequestBuilder.getRequest());

            default:
                throw new Exception();
        }
    }

    private ResultActions requestAccount(MockHttpServletRequestBuilder request) throws Exception {
        return mvc.perform(request);
    }
}
