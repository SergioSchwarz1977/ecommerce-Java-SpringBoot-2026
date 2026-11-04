package com.ecommerce.tienda.controllers;

import com.ecommerce.tienda.entity.Producto;
import com.ecommerce.tienda.service.ProductoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductoControllers {

    ProductoService productoService;

    public ProductoControllers(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping()
    public ResponseEntity<List<Producto>>listarProductos() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.buscarProductoPorId(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarProducto(@RequestBody Producto p,  @PathVariable Long id) {
        return ResponseEntity.ok(productoService.editarProducto(p, id));
    }

    @PostMapping("/crear")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto p) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardarProducto(p));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
       productoService.eliminarProducto(id);
       return ResponseEntity.ok("Producto eliminado correctamente...");
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> listarProductosPorCategoria(@PathVariable String categoria) {
        List<Producto> p = productoService.buscarProductoPorCategoria(categoria);
        return ResponseEntity.ok(p);
    }

}
