package com.wwt.tasklist.task;

import com.wwt.tasklist.user.AppUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> allTasks() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @GetMapping("/personal")
    public ResponseEntity<List<Task>> personalTasks(AppUser appUser) {
        return ResponseEntity.ok(taskService.findAllTasks(appUser));
    }

    @PostMapping
    @Secured("ROLE_USER")
    public ResponseEntity<Task> create(@RequestBody NewTaskRequest newTaskRequest,
                                       @AuthenticationPrincipal AppUser appUser) {
        return taskService.save(newTaskRequest, appUser)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.unprocessableEntity().build());
    }
}
