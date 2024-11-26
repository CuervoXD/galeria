package com.usta.bibliotecaa.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "OBRA")
public class ObraEntity {
    //ID, TITULO,DESCRIPCION,AÑO,TECNICA,FOTO
    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obras")
    private Long idObra;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "titulo_obra", length = 60, nullable = false)
    private String tituloObra;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "descripcion_obra", length = 60, nullable = false)
    private String descripcionObra;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaPub")
    private Date fechaPub;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "tecnica_obra", length = 60, nullable = false)
    private String tecnicaObra;

    @Size(min = 1,max = 200)
    @Column(name = "foto_obra", length = 200)
    private String fotoObra;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "obra_autores",joinColumns = @JoinColumn(name = "id_obras", referencedColumnName = "id_obras"),
            inverseJoinColumns = @JoinColumn(name = "id_artista", referencedColumnName = "id_artista"))
    private Collection<ArtistaEntity> artistas;

    @Override
    public String toString() { return "obra" + tituloObra;}
}
