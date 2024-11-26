package com.usta.bibliotecaa.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "ARTISTA")
public class ArtistaEntity implements Serializable {
    //id,nombre,apellido,pais,fecha,disciplina
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artista")
    private long idArtista;

    @NotNull
    @Size(min=1 , max=40)
    @Column(name = "nombre_art", length = 60, nullable = false)
    private String nombreArtista;

    @NotNull
    @Size(min=1 , max=40)
    @Column(name = "apellido_art", length = 60, nullable = false)
    private String apellidoArtista;

    @Size(min = 1,max = 50)
    @Column(name = "pais", length = 50)
    private String paisArtista;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaNacimiento")
    private Date fechaNacimiento;

    @ManyToMany(mappedBy = "artistas")
    private Set<ObraEntity> obra;
}
