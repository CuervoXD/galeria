package com.usta.bibliotecaa.models.services;

import com.usta.bibliotecaa.entities.ExposicionEntity;
import com.usta.bibliotecaa.entities.ObraEntity;

import java.util.List;

public interface ObraService {

    public List<ObraEntity> findAll();
    public void save(ObraEntity obra);
    public ObraEntity findById(Long id);
    public void delete(Long id);
    public ObraEntity actualizarObraEntity(ObraEntity obra);
    public ObraEntity viewDetail(Long id);
}
