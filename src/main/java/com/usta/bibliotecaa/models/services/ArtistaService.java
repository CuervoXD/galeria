package com.usta.bibliotecaa.models.services;

import com.usta.bibliotecaa.entities.ArtistaEntity;

import java.util.List;

public interface ArtistaService {
    public List<ArtistaEntity> findAll();
    public void save(ArtistaEntity artista);
    public ArtistaEntity findById(Long id);
    public void deleteById(Long id);
    public ArtistaEntity actualizarArtistaEntity(ArtistaEntity artista);
    public ArtistaEntity viewDetail(Long id);


}
