package com.ecommerce.tienda.service;

import com.ecommerce.tienda.entity.Categoria;
import com.ecommerce.tienda.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaRepository getCategoriaRepository() {
        return categoriaRepository;
    }

    public List<Categoria>findAllByCategoria(String categoria){
        return categoriaRepository.findAll();
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(int id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deleteById(int id) {
        categoriaRepository.deleteById(id);
    }

}
