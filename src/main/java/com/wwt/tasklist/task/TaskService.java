package com.wwt.tasklist.task;

import com.wwt.tasklist.user.AppUser;
import com.wwt.tasklist.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> findAllTasks(AppUser appUser) {
        return taskRepository.findByUser(appUser);
    }

    public Optional<Task> save(NewTaskRequest newTaskRequest, Authentication authentication) {
        Task task = new Task();
        task.setDescription(newTaskRequest.getDescription());
        task.setTitle(newTaskRequest.getTitle());
        task.setDue(newTaskRequest.getDue());
        task.setUser(userRepository.findByUsername(authentication.getName()).orElseThrow(IllegalStateException::new));
        return Optional.of(taskRepository.save(task));
    }
}
