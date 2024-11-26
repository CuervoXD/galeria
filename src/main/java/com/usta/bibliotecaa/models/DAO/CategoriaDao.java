package com.usta.bibliotecaa.models.DAO;

import com.usta.bibliotecaa.entities.ArtistaEntity;
import com.usta.bibliotecaa.entities.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoriaDao extends JpaRepository<CategoriaEntity, Long> {
    @Transactional
    @Query("SELECT c FROM CategoriaEntity c WHERE c.idCategoria = ?1")
    List<CategoriaEntity> findByIdCategoria(Long idCategoria);
}
