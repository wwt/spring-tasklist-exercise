package com.wwt.tasklist.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wwt.tasklist.user.AuthenticatedUser;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private AuthenticatedUser user;
    @CreatedDate
    private LocalDateTime createdTime;
    @Nullable
    private LocalDateTime due;

    public Task() {
    }

    public Task(UUID id, @NotEmpty String title, @NotEmpty String description, AuthenticatedUser user, LocalDateTime due) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.due = due;
    }

    public UUID getId() {
        return id;
    }

    @JsonProperty("user")
    public String getUsername() {
        return user.getUsername();
    }

    public void setId(UUID id) {
        this.id = id;
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

    @Nullable
    public LocalDateTime getDue() {
        return due;
    }

    public void setDue(@Nullable LocalDateTime due) {
        this.due = due;
    }

    public AuthenticatedUser getUser() {
        return user;
    }

    public void setUser(AuthenticatedUser authenticatedUser) {
        this.user = authenticatedUser;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", due=" + due +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(title, task.title) &&
                Objects.equals(description, task.description) &&
                Objects.equals(due, task.due);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, due);
    }
}
