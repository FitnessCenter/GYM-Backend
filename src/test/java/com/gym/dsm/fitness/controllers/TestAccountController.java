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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class)
public class TestAccountController {
    private String url = "/account";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void testCreateAccount() throws Exception {
        ResultActions resultActions = requestFactory("POST");

        resultActions
                .andExpect(status().isOk())
                .andDo(print());
    }

    private ResultActions requestFactory(String method) throws Exception{
        switch (method) {
            case "POST":
                Object account = Account.builer()
                .studentNumber("1101")
                .studentName("김어진")
                .id("eojindev")
                .password("p@ssword")
                .sex(true);

                return postAccount(account);

            default:
                throw new Exception();
        }
    }

    private ResultActions postAccount(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        return mvc.perform(post(this.url)
            .contentType(MediaType.APPLICATION_JSON)
            .header("X-Content-Type-Options", "nosniff")
            .content(objectMapper.writeValueAsString(object)));
    }
}
