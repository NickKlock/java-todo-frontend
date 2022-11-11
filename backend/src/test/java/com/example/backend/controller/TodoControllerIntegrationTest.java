package com.example.backend.controller;

import com.example.backend.model.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerIntegrationTest {

    String endPoints = "/api/todo/";
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void allTodo_expect_empty() throws Exception {
        mvc.perform(get(endPoints))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        []
                        """));
    }

    @Test
    void byId_expect_NotFound() throws Exception {
        mvc.perform(get(endPoints + "{id}", "0"))
                .andExpect(status().isNotFound());
    }

    @Test
    void newTodo_expect_properTodo_and_Proper_Id() throws Exception {
        MvcResult result = mvc.perform(post(endPoints)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "description": "test",
                                "status": "OPEN"
                                }
                                """))
                .andExpect(status().isOk())
                .andReturn();

        Todo parsedResult = objectMapper.readValue(result.getResponse().getContentAsString(), Todo.class);
        assertInstanceOf(Todo.class, parsedResult);
        assertFalse(parsedResult.id().isEmpty());
        assertFalse(parsedResult.id().isBlank());

    }

    @Test
    void updateTodo() throws Exception {

        mvc.perform(put(endPoints+"{id}", "0")
                        .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "id": "0",
                        "description": "test",
                        "status": "OPEN"
                        }
                        """))
                .andExpect(status().isNotFound());

    }

    @Test
    void deleteTodo() throws Exception {
        mvc.perform(delete(endPoints+"{id}", "0"))
                .andExpect(status().isNotFound());
    }
}