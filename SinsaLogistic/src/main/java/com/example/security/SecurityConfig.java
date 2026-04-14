package com.example.security;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/*
Ángel Rodríguez Vargas
*/
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider(UserDetailsServiceImpl userDetailsService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // 🟢 PUBLICAS
                .requestMatchers("/", "/login", "/error", "/css/**", "/js/**").permitAll()
                // 🔴 SOLO ADMIN
                .requestMatchers("/material/eliminar/**").hasRole("ADMIN")
                .requestMatchers("/material/nuevo").hasRole("ADMIN")
                .requestMatchers("/material/guardar").hasRole("ADMIN")
                .requestMatchers("/material/modificar/**").hasRole("ADMIN")
                // 🟡 CATALOGO - CLIENTE Y ADMIN
                .requestMatchers("/catalogo/**").hasAnyRole("ADMIN", "CLIENTE")
                // 🟡 ADMIN y CLIENTE
                .requestMatchers("/cliente/**").hasAnyRole("ADMIN", "CLIENTE")
                .requestMatchers("/cotizacion/**").hasAnyRole("ADMIN", "CLIENTE")
                // 🔵 TODO LO DEMÁS
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {
                    boolean isAdmin = authentication.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                    if (isAdmin) {
                        response.sendRedirect("/cotizacion/listado");
                    } else {
                        response.sendRedirect("/catalogo/materiales");
                    }
                })
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login")
                .permitAll()
            );
        return http.build();
    }
}