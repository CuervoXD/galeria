package com.usta.bibliotecaa.models.DAO;

import com.usta.bibliotecaa.entities.ExposicionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ExposicionDao extends JpaRepository<ExposicionEntity, Long> {

    @Transactional
    @Query("SELECT EX FROM ExposicionEntity EX WHERE EX.idExposicion=?1")
    public ExposicionEntity viewDetail(Long idExposicion);

}
