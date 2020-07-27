package com.wwt.tasklist.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wwt.tasklist.user.AppUser;
import com.wwt.tasklist.user.Authority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void shouldReturnAllTasksWhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\": \"aa0e9504-22fd-41d4-b829-c6d47c837961\",\n" +
                        "    \"title\": \"Test 1\",\n" +
                        "    \"description\": \"I should complete this task 1\",\n" +
                        "    \"due\": null\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": \"31e37df2-ebf4-4e7b-bb8f-4c6dd6ca7f39\",\n" +
                        "    \"title\": \"Test 2\",\n" +
                        "    \"description\": \"I should complete this task 2\",\n" +
                        "    \"due\": null\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": \"e2f82fce-8e0b-4333-bd97-2c7f136128b5\",\n" +
                        "    \"title\": \"Test 3\",\n" +
                        "    \"description\": \"I should complete this task 3\",\n" +
                        "    \"due\": null\n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    @WithMockUser(username = "bob")
    void shouldBeAbleToCreateTaskAsAuthenticatedUser() throws Exception {
        NewTaskRequest todo = new NewTaskRequest("Title", "Description", null);

        mockMvc.perform(post("/tasks")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todo)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}