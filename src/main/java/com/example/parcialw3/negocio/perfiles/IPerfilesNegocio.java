package com.example.parcialw3.negocio.perfiles;

import com.example.parcialw3.modelo.Perfiles;
import com.example.parcialw3.negocio.exceptions.EncontradoException;
import com.example.parcialw3.negocio.exceptions.NegocioException;
import com.example.parcialw3.negocio.exceptions.NoEncontradoException;

import java.util.List;

public interface IPerfilesNegocio {
    List<Perfiles> listado() throws NegocioException;

    Perfiles agregar(Perfiles perfiles) throws NegocioException, EncontradoException, NoEncontradoException;

    Perfiles cargar(long id) throws NegocioException, NoEncontradoException;

    Perfiles modificar(Perfiles perfiles) throws NegocioException, NoEncontradoException;

    void eliminar(long id) throws NegocioException, NoEncontradoException;

    List<Perfiles>  listarNombres( String nombre ) throws NegocioException;

    List<Perfiles>  listarDireccion( String direccion ) throws NegocioException;

}
