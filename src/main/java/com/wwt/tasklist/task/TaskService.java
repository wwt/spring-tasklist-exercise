package com.wwt.tasklist.task;

import com.wwt.tasklist.user.AppUser;
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

    public List<Task> findAllTasks(AppUser appUser) {
        return taskRepository.findByUser(appUser);
    }

    public Optional<Task> save(NewTaskRequest newTaskRequest, AppUser appUser) {
        Task task = new Task();
        task.setDescription(newTaskRequest.getDescription());
        task.setTitle(newTaskRequest.getTitle());
        task.setDue(newTaskRequest.getDue());
        task.setUser(appUser);
        return Optional.of(taskRepository.save(task));
    }
}
