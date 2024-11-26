package com.usta.bibliotecaa.models.services;

import com.usta.bibliotecaa.entities.ExposicionEntity;
import com.usta.bibliotecaa.entities.RolEntity;
import com.usta.bibliotecaa.models.DAO.ExposicionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExposicionServiceImplement implements ExposicionService {

    @Autowired
    private ExposicionDao exposicionDao;

    @Override
    @Transactional
    public List<ExposicionEntity> findAll() {
        return (List<ExposicionEntity>) exposicionDao.findAll();
    }

    @Override
    @Transactional
    public void save(ExposicionEntity exposicion) {
        exposicionDao.save(exposicion);
    }

    @Override
    @Transactional
    public ExposicionEntity findById(Long id) {
        return exposicionDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        exposicionDao.deleteById(id);
    }

    @Override
    @Transactional
    public ExposicionEntity actualizarExposicionEntity(ExposicionEntity exposicion) {
        return exposicionDao.save(exposicion);
    }

    @Override
    @Transactional
    public ExposicionEntity viewDetail(Long id) {
        return exposicionDao.viewDetail(id);
    }
}
