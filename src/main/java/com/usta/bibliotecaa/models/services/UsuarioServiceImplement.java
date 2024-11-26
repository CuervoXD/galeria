package com.usta.bibliotecaa.models.services;

import com.usta.bibliotecaa.entities.UsuarioEntity;
import com.usta.bibliotecaa.models.DAO.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UsuarioServiceImplement implements UsuarioService{

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public List<UsuarioEntity> findAll() {
        List <UsuarioEntity> usuarios = usuarioDao.findAll();
        usuarios.sort(Comparator.comparing(UsuarioEntity::getNombreUsuario));
        return usuarios;
    }

    @Override
    public void save(UsuarioEntity usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    public UsuarioEntity findById(Long id) {
        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        usuarioDao.deleteById(id);
    }

    @Override
    public UsuarioEntity actualizarUsuarioEntity(UsuarioEntity usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    public UsuarioEntity findByEmail(String email) {
        return usuarioDao.findByEmail(email);
    }
}
