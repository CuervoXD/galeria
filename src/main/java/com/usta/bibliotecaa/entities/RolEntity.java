package com.usta.bibliotecaa.entities;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(name="ROLES")
public class RolEntity {

    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Rol")
    private Long idRol;

    @NotNull
    @Size(min = 1,max = 40)
    @Column(name = "Rol", length = 40, nullable = false)
    private String Rol;

    public RolEntity(String Rol) {
        super();
        this.Rol = Rol;
    }

    public RolEntity() {

    }


}