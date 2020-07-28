package com.wwt.tasklist.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="users")
public class AuthenticatedUser implements UserDetails {
    @Id
    private UUID id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Authority> authorities;

    private boolean locked;
    private boolean expired;
    private boolean enabled;

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !expired;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                ", locked=" + locked +
                ", expired=" + expired +
                ", enabled=" + enabled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) o;
        return locked == authenticatedUser.locked &&
                expired == authenticatedUser.expired &&
                enabled == authenticatedUser.enabled &&
                Objects.equals(id, authenticatedUser.id) &&
                Objects.equals(username, authenticatedUser.username) &&
                Objects.equals(password, authenticatedUser.password) &&
                Objects.equals(authorities, authenticatedUser.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, authorities, locked, expired, enabled);
    }
}
