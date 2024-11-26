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
@Table(name = "EXPOSICION")
public class ExposicionEntity {
    //TITULO,DESCRIPCION,FECHA INICIO,FECHA FIN
    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exposicion")
    private Long idExposicion;

    @NotNull
    @Size(min = 1,max = 40)
    @Column(name = "titulo_expo", length = 60,nullable = false)
    private String tituloExpo;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "descripcion_expo", length = 60, nullable = false)
    private String descripcionExpo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaInicioExposicion")
    private Date fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaFinExposicion")
    private Date fechaFin;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "exposicion_obras",
            joinColumns = @JoinColumn(name = "id_exposicion", referencedColumnName = "id_exposicion"),
            inverseJoinColumns = @JoinColumn(name = "id_obras", referencedColumnName = "id_obras")
    )
    private Collection<ObraEntity> obras;

}
