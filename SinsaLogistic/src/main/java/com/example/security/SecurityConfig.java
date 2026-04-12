package com.example.security;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

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
                .requestMatchers("/", "/login", "/css/**", "/js/**").permitAll()

                // 🔴 SOLO ADMIN
                .requestMatchers("/material/eliminar/**").hasRole("ADMIN")

                // 🟡 ADMIN y CLIENTE
                .requestMatchers("/cliente/**").hasAnyRole("ADMIN","CLIENTE")
                .requestMatchers("/cotizacion/**").hasAnyRole("ADMIN","CLIENTE")

                // 🔵 TODO LO DEMÁS
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/cliente/listado", true)
            )
            .logout(logout -> logout.logoutSuccessUrl("/login"));

        return http.build();
    }
}
