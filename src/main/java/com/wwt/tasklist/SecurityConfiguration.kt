package com.wwt.tasklist

import com.wwt.tasklist.user.JdbcUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration(
    private val userDetailsService: JdbcUserDetailsService
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
                .antMatchers("/tasks").permitAll()
                .anyRequest().hasAnyRole("ADMIN", "USER")
            .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
            .and()
                .logout().permitAll().logoutSuccessUrl("/login")
            .and()
                .csrf().disable()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}