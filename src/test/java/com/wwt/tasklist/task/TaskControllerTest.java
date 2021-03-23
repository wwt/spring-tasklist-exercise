package com.wwt.tasklist.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class TaskControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void shouldReturnAllTasksWhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/tasks"))
            .andExpect(status().isOk())
            .andExpect(content().json("""
                    [
                      {
                        "id": "aa0e9504-22fd-41d4-b829-c6d47c837961",
                        "title": "Test 1",
                        "description": "I should complete this task 1",
                        "due": null,
                        "user": "bob"
                      },
                      {
                        "id": "31e37df2-ebf4-4e7b-bb8f-4c6dd6ca7f39",
                        "title": "Test 2",
                        "description": "I should complete this task 2",
                        "due": null,
                        "user": "steve"
                      },
                      {
                        "id": "e2f82fce-8e0b-4333-bd97-2c7f136128b5",
                        "title": "Test 3",
                        "description": "I should complete this task 3",
                        "due": null,
                        "user": "nate"
                      }
                    ]
                    """))
            .andExpect(content().string(not(contains("password"))));
    }

    @Test
    @WithUserDetails("nate") // see sql files for user definitions
    void shouldBeAbleToGetTasksForSpecificUser() throws Exception {
        mockMvc.perform(get("/tasks/personal"))
            .andExpect(status().isOk())
            .andExpect(content().json("""
                [
                  {
                    "id": "e2f82fce-8e0b-4333-bd97-2c7f136128b5",
                    "title": "Test 3",
                    "description": "I should complete this task 3",
                    "due": null,
                    "user": "nate"
                  }
                ]
            """));
    }

    @Test
    @WithUserDetails("bob") // see sql files for user definitions
    void shouldBeAbleToCreateTaskAsAuthenticatedUser() throws Exception {
        // Note: nanosecond serialization disabled for times, see: application.properties
        LocalDateTime now = LocalDateTime.of(2021, 3, 14, 10, 14, 0, 50);
        NewTaskRequest todo = new NewTaskRequest("Work work!", "I need to do this task too!", now);

        mockMvc.perform(post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(todo)))
            .andDo(print())
            .andExpect(status().isOk());

        mockMvc.perform(get("/tasks/personal"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[1].title").value("Work work!"))
            .andExpect(jsonPath("$[1].description").value("I need to do this task too!"))
            .andExpect(jsonPath("$[1].due").value("2021-03-14T10:14:00"));
    }
}
