package com.wwt.tasklist.task;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Objects;

public class NewTaskRequest {
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    private LocalDateTime due;

    public NewTaskRequest() {
    }

    public NewTaskRequest(@NotEmpty String title, @NotEmpty String description, LocalDateTime due) {
        this.title = title;
        this.description = description;
        this.due = due;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDue() {
        return due;
    }

    public void setDue(LocalDateTime due) {
        this.due = due;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewTaskRequest that = (NewTaskRequest) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(due, that.due);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, due);
    }
}
