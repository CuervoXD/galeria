package com.usta.bibliotecaa.models.services;

import com.usta.bibliotecaa.entities.ObraEntity;
import com.usta.bibliotecaa.models.DAO.ObraDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ObraServiceImplement implements ObraService {

    @Autowired
    public ObraDao obraDao;

    @Override
    public List<ObraEntity> findAll() {

        return obraDao.findAll();
    }

    @Override
    public void save(ObraEntity obra) {
        obraDao.save(obra);
    }

    @Override
    public ObraEntity findById(Long id) {
        return obraDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        obraDao.deleteById(id);
    }

    @Override
    public ObraEntity actualizarObraEntity(ObraEntity obra) {
        return obraDao.save(obra);
    }

    @Override
    public ObraEntity viewDetail(Long id) {
        return obraDao.viewDetail(id);
    }
}
