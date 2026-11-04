package com.ecommerce.tienda.service;

import com.ecommerce.tienda.entity.Producto;
import com.ecommerce.tienda.exception.ProductoNoEncontradoException;
import com.ecommerce.tienda.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    ProductoRepository productoRepository;
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto buscarProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() ->
                        new ProductoNoEncontradoException(
                                "Producto con ID " + id + " no encontrado"));
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
       if(!productoRepository.existsById(id)){
           throw new RuntimeException("No existe el producto con el id: " + id);
       }
       productoRepository.deleteById(id);
    }

    public Producto editarProducto(Producto producto, Long id) {
        Producto p  = productoRepository.findById(id).orElse(null);
        if (p != null) {
            p.setNombre(producto.getNombre());
            p.setDescripcion(producto.getDescripcion());
            p.setCategoria(producto.getCategoria());
            p.setPrecio(producto.getPrecio());
            p.setStock(producto.getStock());
            return productoRepository.save(p);
        }
       return null;
    }

    public List<Producto> buscarProductoPorCategoria(String categoria) {
      return productoRepository.findByCategoria(categoria);
    }
}
