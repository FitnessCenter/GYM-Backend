package com.gym.dsm.fitness.controllers;

import com.gym.dsm.fitness.payloads.requests.CreateAccountRequest;
import com.gym.dsm.fitness.payloads.responses.CreateAccountResponse;
import com.gym.dsm.fitness.payloads.responses.GetAccountResponse;
import com.gym.dsm.fitness.security.JWTProvider;
import com.gym.dsm.fitness.services.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class)
public class TestAccountController {

    private JWTProvider jwtProvider;

    @Autowired
    private MockMvc mvc;

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
                MockRequestBuilder getMockRequestBuilder = MockRequestBuilder.builder()
                        .requestBuilder(get(url))
                        .token(jwtProvider.generateAccessToken("eojindev"))
                        .build();

                GetAccountResponse getMockResponse = GetAccountResponse.builder()
                        .StudentName("김어진")
                        .studentNumber("1101")
                        .build();

                when(accountService.getAccount()).thenReturn(getMockResponse);

                return requestAccount(getMockRequestBuilder.getRequest());

            case "POST":
                CreateAccountRequest createAccountRequest = CreateAccountRequest.builder()
                        .id("eojindev")
                        .password("p@ssword")
                        .studentName("김어진")
                        .studentNumber("1101")
                        .sex(true)
                        .build();


                CreateAccountResponse createMockResponse = CreateAccountResponse.builder()
                        .message("Created")
                        .build();

                when(accountService.createAccount(createAccountRequest)).thenReturn(createMockResponse);

                MockRequestBuilder postMockRequestBuilder = MockRequestBuilder.builder()
                        .requestBuilder(post(url))
                        .body(createAccountRequest)
                        .build();

                return requestAccount(postMockRequestBuilder.getRequest());

//            case "PUT":
//                UpdateAccountRequest updateAccountRequest = UpdateAccountRequest.builder()
//                        .currentPassword("currentP@ssword")
//                        .newPassword("p@ssword")
//                        .build();
//
//                MockRequestBuilder putMockRequestBuilder = MockRequestBuilder.builder()
//                        .requestBuilder(put(url))
//                        .token(jwtProvider.generateAccessToken())
//                        .body(updateAccountRequest)
//                        .build();
//
//                return requestAccount(putMockRequestBuilder.getRequest());

            default:
                throw new Exception();
        }
    }

    private ResultActions requestAccount(MockHttpServletRequestBuilder request) throws Exception {
        return mvc.perform(request);
    }
}
