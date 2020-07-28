package com.wwt.tasklist.user;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="authorities")
public class Authority implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private UUID id;
    @Column(name = "authority")
    private String name;
    @Column(name = "user_id")
    private UUID userId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return Objects.equals(id, authority.id) &&
            Objects.equals(name, authority.name) &&
            Objects.equals(userId, authority.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userId);
    }

    @Override
    public String toString() {
        return "Authority{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", userId=" + userId +
            '}';
    }
}
