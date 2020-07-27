package com.wwt.tasklist.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name="users")
public class AppUser implements UserDetails {
    @Id
    private UUID id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username", fetch = FetchType.EAGER)
    private List<Authority> authorities;

    private boolean locked;
    private boolean expired;
    private boolean enabled;

    public AppUser() {
    }

    public AppUser(@NotEmpty String username, @NotEmpty String password, List<Authority> authorities) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(Authority::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
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
        AppUser appUser = (AppUser) o;
        return locked == appUser.locked &&
                expired == appUser.expired &&
                enabled == appUser.enabled &&
                Objects.equals(id, appUser.id) &&
                Objects.equals(username, appUser.username) &&
                Objects.equals(password, appUser.password) &&
                Objects.equals(authorities, appUser.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, authorities, locked, expired, enabled);
    }
}
