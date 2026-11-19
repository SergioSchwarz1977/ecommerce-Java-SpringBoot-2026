package com.ecommerce.tienda.controllers;

import com.ecommerce.tienda.DTO.DtoProductoCategoria;
import com.ecommerce.tienda.entity.Categoria;
import com.ecommerce.tienda.repository.CategoriaRepository;
import com.ecommerce.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class CategoriaControllers {

    @Autowired
    private ProductoService productoService;

    public CategoriaControllers(ProductoService productoService, CategoriaRepository categoriaRepository) {
        this.productoService = productoService;
    }


    @GetMapping("/listarConCategoria")
    public ResponseEntity<List<DtoProductoCategoria>> listarConCategoria() {
        return ResponseEntity.ok(productoService.listarProductosConCategoria());
    }
}
