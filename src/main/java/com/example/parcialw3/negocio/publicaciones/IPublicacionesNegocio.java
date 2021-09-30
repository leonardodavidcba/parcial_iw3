package com.example.parcialw3.negocio.publicaciones;

import com.example.parcialw3.modelo.Perfiles;
import com.example.parcialw3.modelo.Publicacion;
import com.example.parcialw3.negocio.exceptions.EncontradoException;
import com.example.parcialw3.negocio.exceptions.NegocioException;
import com.example.parcialw3.negocio.exceptions.NoEncontradoException;

import java.util.List;

public interface IPublicacionesNegocio {
    List<Publicacion> listado() throws NegocioException;

    Publicacion agregar(Publicacion publicacion) throws NegocioException, EncontradoException, NoEncontradoException;

    Publicacion cargar(long id) throws NegocioException, NoEncontradoException;

    Publicacion modificar(Publicacion publicacion) throws NegocioException, NoEncontradoException;

    void eliminar(long id) throws NegocioException, NoEncontradoException;

    List<Publicacion>  listarNombres(String nombre ) throws NegocioException;

   // List<Publicacion>  listarLikes( int like ) throws NegocioException;

}
