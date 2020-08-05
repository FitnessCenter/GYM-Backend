package com.gym.dsm.fitness.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class RequestBuilder {
    private MockHttpServletRequestBuilder requestBuilder;
    private String token;
    private Object body;

    @Builder
    public RequestBuilder(MockHttpServletRequestBuilder requestBuilder, String token, Object body) throws Exception {
        this.requestBuilder = requestBuilder;
        this.token = token;
        this.body = body;
    }

    public MockHttpServletRequestBuilder getRequest() throws Exception {
        return requestMapping();
    }

    private MockHttpServletRequestBuilder requestMapping() throws Exception {
        MockHttpServletRequestBuilder request = requestBuilder.contentType(MediaType.APPLICATION_JSON);
        if (token != null) request.header("Authorzation", token);
        if (body != null) request.content(jsonMapper(body));
        return request;
    }

    private String jsonMapper(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
