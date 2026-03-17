/**
 *
 * Pablo Romero
 */


package com.example.repository;

import com.example.domain.Material;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MaterialRepository extends JpaRepository<Material, Integer> {

    List<Material> findByStockLessThanEqual(Integer stock);
    
}
