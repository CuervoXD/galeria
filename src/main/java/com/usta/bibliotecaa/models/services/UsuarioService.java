package com.usta.bibliotecaa.models.services;

import com.usta.bibliotecaa.entities.UsuarioEntity;

import java.util.List;

public interface UsuarioService {

    public List<UsuarioEntity> findAll();
    public void save(UsuarioEntity usuario);
    public UsuarioEntity findById(Long id);
    public void deleteById(Long id);
    public UsuarioEntity actualizarUsuarioEntity(UsuarioEntity usuario);
    public UsuarioEntity findByEmail(String email);

}
