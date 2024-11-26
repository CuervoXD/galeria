package com.usta.bibliotecaa.models.DAO;

import com.usta.bibliotecaa.entities.ObraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ObraDao extends JpaRepository<ObraEntity, Long> {

    @Transactional
    @Query("SELECT OE FROM ObraEntity OE WHERE OE.idObra=?1")
    public ObraEntity viewDetail(Long idObra);

}
