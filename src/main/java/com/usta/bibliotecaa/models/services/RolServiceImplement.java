package com.usta.bibliotecaa.models.services;

import com.usta.bibliotecaa.entities.RolEntity;
import com.usta.bibliotecaa.models.DAO.RolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImplement implements RolService {

    @Autowired
    private RolDao rolDao;

    @Override
    public List<RolEntity> findAll() {
        return (List<RolEntity>) rolDao.findAll();
    }

    @Override
    public void save(RolEntity rol) {
        rolDao.save(rol);
    }

    @Override
    public RolEntity findById(Long id) {
        return rolDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        rolDao.deleteById(id);
    }

    @Override
    public RolEntity actualizarRolEntity(RolEntity rol) {
        return rolDao.save(rol);
    }
}
