package com.usta.bibliotecaa.models.services;

import com.usta.bibliotecaa.entities.ArtistaEntity;
import com.usta.bibliotecaa.models.DAO.ArtistaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class ArtistaServiceImplement implements ArtistaService {

    @Autowired
    private ArtistaDao artistaDao;

    @Override
    @Transactional
    public List<ArtistaEntity> findAll() {
        List<ArtistaEntity> artistas = artistaDao.findAll();
        artistas.sort(Comparator.comparing(ArtistaEntity::getNombreArtista).thenComparing(ArtistaEntity::getApellidoArtista));
        return artistas;
    }

    @Override
    @Transactional
    public void save(ArtistaEntity artista) {
        artistaDao.save(artista);
    }

    @Override
    @Transactional
    public ArtistaEntity findById(Long id) {
        return artistaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        artistaDao.deleteById(id);
    }

    @Override
    public ArtistaEntity actualizarArtistaEntity(ArtistaEntity artista) {
        return artistaDao.save(artista);
    }

    @Override
    @Transactional(readOnly = true)
    public ArtistaEntity viewDetail(Long id) {
        return artistaDao.viewDetail(id);
    }

}
