package com.wwt.tasklist.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import(JdbcUserDetailsService.class)
class JdbcUserDetailsServiceTest {
    @Autowired
    private JdbcUserDetailsService jdbcUserDetailsService;

    @Test
    void whenUserFoundReturnIt() {
        String username = "bob";

        AuthenticatedUser bob = jdbcUserDetailsService.loadUserByUsername(username);

        assertThat(bob.getAuthorities()).hasSize(1);
        assertThat(bob.getAuthorities().get(0).getAuthority()).isEqualTo("ROLE_USER");
    }

    @Test
    void whenUserNotFoundShouldThrowUsernameNotFoundException() {
        assertThrows(UsernameNotFoundException.class, () -> jdbcUserDetailsService.loadUserByUsername("not a user"));
    }
}
