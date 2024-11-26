package com.usta.bibliotecaa.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "USUARIOS")
public class UsuarioEntity {
    // idusuario, nombreusuario, correousuario, contraseña

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_usuario", length = 60, nullable = false)
    private String nombreUsuario;

    @NotNull
    @Size(min = 1,max = 100)
    @Column(name = "email", unique = true,length = 100, nullable = false)
    private String email;

    @NotNull
    @Size(min = 1,max = 15)
    @Column(name = "clave", length = 15, nullable = false)
    private String clave;

    @NotNull
    @JoinColumn(name = "id_Rol", referencedColumnName = "id_Rol")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RolEntity rol;


}
