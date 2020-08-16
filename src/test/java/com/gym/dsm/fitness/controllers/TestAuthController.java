package com.gym.dsm.fitness.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AuthController.class)
@ContextConfiguration
public class TestAuthController {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testSignIn() throws Exception {
        ResultActions resultActions = requestFactory("POST");

        resultActions
                .andExpect(status().isOk())
                .andDo(print());

    }

    private ResultActions requestFactory(String method) throws Exception{
        String url = "/auth";
        switch (method) {
            case "POST":
                SignInRequest signInRequest = SignInRequest.builder()
                        .id("eojindev")
                        .password("p@ssword")
                        .build();

                SignInResponse signInResponse = signInResponse.builder()
                        .accessToken("eya.b.c")
                        .refreshToken("eya.b.d")
                        .build();

                when(AuthService.SignIn(signInRequest)).thenReturn(signInResponse);

                MockRequestBuilder signInRequestBuilder = MockRequestBuilder.builder()
                        .requestBuilder(post(url))
                        .body(signInRequest)
                        .build();

                return requestAccount(signInRequestBuilder.getRequest());

            default:
                throw new Exception();
        }
    }

    private ResultActions requestAccount(MockHttpServletRequestBuilder request) throws Exception {
        return mvc.perform(request);
    }
}
