package com.ecommerce.tienda.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DtoProductoCategoria {
    private int id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private int stock;
    private String categoria;

    public DtoProductoCategoria() {
    }

    public DtoProductoCategoria(int id, String nombre, String descripcion, Double precio, int stock, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }
}