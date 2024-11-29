package com.flight.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.concurrent.TimeUnit;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/flights", "/admin/report-flights-list").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(httpBasic -> {})
                .sessionManagement(session -> session
                        .sessionFixation().newSession()
                        .maximumSessions(1)
                        .expiredSessionStrategy(request -> {
                            request.getResponse().sendRedirect("/login?expired=true");
                        })
                )
                .rememberMe(r -> r
                        .tokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(1))
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("ibrahim")
                        .password(passwordEncoder().encode("12345"))
                        .roles("ADMIN")
                        .build()
        );
    }
}