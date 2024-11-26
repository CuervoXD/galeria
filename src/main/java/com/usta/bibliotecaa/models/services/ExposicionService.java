package com.usta.bibliotecaa.models.services;

import com.usta.bibliotecaa.entities.ArtistaEntity;
import com.usta.bibliotecaa.entities.CategoriaEntity;
import com.usta.bibliotecaa.entities.ExposicionEntity;

import java.util.List;

public interface ExposicionService {

    public List<ExposicionEntity> findAll();
    public void save(ExposicionEntity exposicion);
    public ExposicionEntity findById(Long id);
    public void delete(Long id);
    public ExposicionEntity actualizarExposicionEntity(ExposicionEntity exposicion);
    public ExposicionEntity viewDetail(Long id);
}
