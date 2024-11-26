package com.usta.bibliotecaa.entities;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Entity
@Table(name = "CATEGORIA")
public class CategoriaEntity {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_categoria", length = 50, nullable = false)
    private String nombreCategoria;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "categoria_exposicion",joinColumns = @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria"),
            inverseJoinColumns = @JoinColumn(name = "id_exposicion", referencedColumnName = "id_exposicion"))
    private Collection<ExposicionEntity> exposiciones;

}
