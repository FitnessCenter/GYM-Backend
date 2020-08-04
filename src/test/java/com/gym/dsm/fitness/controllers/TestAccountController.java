package com.gym.dsm.fitness.controllers;

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
        ResultActions actions = requestFactory("POST");
    }

    private ResultActions requestFactory(String method) throws Exception{
        switch (method) {
            case "POST":
                String requestData = jsonMapper("1101", "김어진", "eojindev", "p@ssword", true);
                return this.postAccount(requestData);
            default:
                throw new Exception();
        }
    }

    private ResultActions postAccount(String content) throws Exception {
        return mvc.perform(post(this.url)
            .contentType(MediaType.APPLICATION_JSON)
            .header("X-Content-Type-Options", "nosniff")
            .content(content));
    }

    private String jsonMapper(String studentNumber, String studentName, String id, String password, Boolean sex) {
        return "";
    }
}
