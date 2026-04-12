package com.example.repository;

import com.example.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/*
Ángel Felipe Rodríguez Vargas
*/

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
}
