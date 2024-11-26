package com.usta.bibliotecaa.models.services;

import com.usta.bibliotecaa.entities.CategoriaEntity;
import com.usta.bibliotecaa.models.DAO.CategoriaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaServiceImplement implements CategoriaService {

    @Autowired
    public CategoriaDao categoriaDao;
    @Override
    @Transactional
    public List<CategoriaEntity> findAll() {
        return (List<CategoriaEntity>) categoriaDao.findAll();
    }

    @Override
    @Transactional
    public void save(CategoriaEntity categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    @Transactional
    public CategoriaEntity findById(Long id) {
        return categoriaDao.findById(id).get();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoriaDao.deleteById(id);
    }

    @Override
    @Transactional
    public CategoriaEntity actualizarCategoriaEntity(CategoriaEntity categoria) {
        return categoriaDao.save(categoria);
    }
}
