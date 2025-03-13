package com.taxcalc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Libera endpoints públicos
                        .requestMatchers("/user/register", "/user/login", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/tipos", "/tipos/**").hasRole("ADMIN")
                        .requestMatchers("/calculo").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
                    //permitAll()
                     //   .anyRequest().authenticated()
                    // );
//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http
//                    .csrf(csrf -> csrf.disable())
//                    .authorizeHttpRequests(auth -> auth
//                            .anyRequest().permitAll() // ⚠️ Remove autenticação temporariamente
//                    );
//            return http.build();
//        }

        return http.build();
    }
}