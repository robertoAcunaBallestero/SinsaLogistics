/**
 *
 * 
 */

package com.example.repository;

import com.example.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
    //Usamos Spring para crea la consulta automáticamente por el nombre del método
    //Se usa el Útil para cuando un cliente quiera iniciar sesión o buscar sus datos
    Cliente findByCorreo(String correo);
}
