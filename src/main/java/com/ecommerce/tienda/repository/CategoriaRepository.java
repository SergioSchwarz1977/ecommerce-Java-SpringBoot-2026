package com.ecommerce.tienda.repository;

import com.ecommerce.tienda.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
    Optional<Categoria> findByCategoria(String categoria);
}
