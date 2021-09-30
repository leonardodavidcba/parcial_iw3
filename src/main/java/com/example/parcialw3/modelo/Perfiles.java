package com.example.parcialw3.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="perfiles")
public class Perfiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // aca voy a conectar el producto con un rubro
    @OneToOne(fetch = FetchType.LAZY)   // uno a uno
    @JoinColumn(name = "id_publicacion")
    private Publicacion publicacion;

    @Column(length = 50)
    private String nombreUsuario;

    @Column(length = 50)
    private String direccion;

    @Column(length = 50)
    private Date fechaNacimiento;

    @Column(length = 50)
    private int cantSeguidores;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getCantSeguidores() {
        return cantSeguidores;
    }

    public void setCantSeguidores(int cantSeguidores) {
        this.cantSeguidores = cantSeguidores;
    }
}
