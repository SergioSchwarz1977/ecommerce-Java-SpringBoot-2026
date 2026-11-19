package com.ecommerce.tienda.service;

import com.ecommerce.tienda.DTO.DtoProductoCategoria;
import com.ecommerce.tienda.entity.Producto;
import com.ecommerce.tienda.exception.ProductoNoEncontradoException;
import com.ecommerce.tienda.repository.CategoriaRepository;
import com.ecommerce.tienda.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    ProductoRepository productoRepository;
    CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public List<DtoProductoCategoria> listarProductosConCategoria() {
        return productoRepository.findAll().stream()
                .map(producto -> new DtoProductoCategoria(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getDescripcion(),
                        producto.getPrecio(),
                        producto.getStock(),
                        producto.getCategoria() == null
                                ? null
                                : producto.getCategoria().getCategoria()))
                .collect(Collectors.toList());
    }

    public Producto buscarProductoPorId(int id) {
        return productoRepository.findById(id).orElse(null);
    }


    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminarProducto(int id) {
       if(!productoRepository.existsById(id)){
           throw new RuntimeException("No existe el producto con el id: " + id);
       }
       productoRepository.deleteById(id);
    }

    public Producto editarProducto(Producto producto, int id) {
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
