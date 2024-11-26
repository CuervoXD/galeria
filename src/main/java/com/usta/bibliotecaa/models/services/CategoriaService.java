package com.usta.bibliotecaa.models.services;

import com.usta.bibliotecaa.entities.CategoriaEntity;

import java.util.List;

public interface CategoriaService {

    public List<CategoriaEntity> findAll();
    public void save(CategoriaEntity categoria);
    public CategoriaEntity findById(Long id);
    public void deleteById(Long id);
    public CategoriaEntity actualizarCategoriaEntity(CategoriaEntity categoria);

}
