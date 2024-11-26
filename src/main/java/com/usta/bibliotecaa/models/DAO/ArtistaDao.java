package com.usta.bibliotecaa.models.DAO;

import com.usta.bibliotecaa.entities.ArtistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ArtistaDao extends JpaRepository<ArtistaEntity, Long> {


    @Transactional
    @Query("SELECT AU FROM ArtistaEntity AU WHERE AU.idArtista=?1")
    public ArtistaEntity viewDetail(Long idArtista);


}
