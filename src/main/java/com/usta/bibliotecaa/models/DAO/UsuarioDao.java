package com.usta.bibliotecaa.models.DAO;

import com.usta.bibliotecaa.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UsuarioDao extends JpaRepository<UsuarioEntity, Long> {

    @Transactional
    @Query("SELECT US FROM UsuarioEntity US WHERE US.email=?1")
    public UsuarioEntity findByEmail(String email);

}
