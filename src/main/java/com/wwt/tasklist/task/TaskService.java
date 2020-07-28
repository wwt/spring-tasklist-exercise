package com.wwt.tasklist.task;

import com.wwt.tasklist.user.AuthenticatedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> findAllTasks(AuthenticatedUser authenticatedUser) {
        return taskRepository.findByUser(authenticatedUser);
    }

    public Optional<Task> save(NewTaskRequest newTaskRequest, AuthenticatedUser authenticatedUser) {
        Task task = new Task();
        task.setDescription(newTaskRequest.getDescription());
        task.setTitle(newTaskRequest.getTitle());
        task.setDue(newTaskRequest.getDue());
        task.setUser(authenticatedUser);
        return Optional.of(taskRepository.save(task));
    }
}
