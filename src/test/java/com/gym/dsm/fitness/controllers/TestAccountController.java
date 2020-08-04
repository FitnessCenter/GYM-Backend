package com.gym.dsm.fitness.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class)
public class TestAccountController {
    private String url = "/account";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JWTProvider jwtProvider;

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
        switch (method) {
            case "GET":
                return getAccount(jwtProvider.generateAccessToken());

            case "POST":
                Object createAccountRequest = new CreateAccountRequest("1101", "김어진", "eojindev", "p@ssword", true);
                return postAccount(createAccountRequest);

            case "PUT":
                Object updateAccountRequest = new UpdateAccountRequest("currentlyP@ssword", "p@ssword");
                return postAccount(updateAccountRequest, jwtProvider.generateAccessToken());
            default:
                throw new Exception();
        }
    }

    private ResultActions getAccount(String token) throws Exception {
        return mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token));
    }

    private ResultActions postAccount(Object body) throws Exception {
        return mvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonMapper(body)));
    }

    private ResultActions updateAccount(Object body, String token) throws Exception {
        return mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
                .content(jsonMapper(body)));
    }

    private String jsonMapper(Object object) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(object);
    }
}
