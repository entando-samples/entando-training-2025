package com.entando.springbootexpense.config;

import com.entando.springbootexpense.config.jwt.JwtAuthConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    public static final String EXPENSE_BUNDLE_API = "/api/expenses/**";
    public final JwtAuthConverter jwtAuthConverter;

    public SecurityConfiguration(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) //NOSONAR
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.OPTIONS, EXPENSE_BUNDLE_API).permitAll()
                        .requestMatchers(HttpMethod.GET, EXPENSE_BUNDLE_API).permitAll()
                        .requestMatchers(HttpMethod.PATCH, EXPENSE_BUNDLE_API).permitAll()
                        .requestMatchers(HttpMethod.POST, EXPENSE_BUNDLE_API).permitAll()
                        .requestMatchers(EXPENSE_BUNDLE_API).authenticated()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }


}
