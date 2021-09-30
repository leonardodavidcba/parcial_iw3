package com.example.parcialw3.negocio.publicaciones;

import com.example.parcialw3.modelo.Publicacion;
import com.example.parcialw3.negocio.exceptions.EncontradoException;
import com.example.parcialw3.negocio.exceptions.NegocioException;
import com.example.parcialw3.negocio.exceptions.NoEncontradoException;
import com.example.parcialw3.negocio.perfiles.PerfilesNegocio;
import com.example.parcialw3.percistencia.PublicacionesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionesNegocio  implements  IPublicacionesNegocio{
    private Logger log = LoggerFactory.getLogger(PerfilesNegocio.class);

    @Autowired
    private PublicacionesRepository publicacionesDAO;


    @Override
    public List<Publicacion> listado() throws NegocioException {
        try {
            return publicacionesDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new NegocioException(e);
        }
    }

    @Override
    public Publicacion agregar(Publicacion publicacion) throws NegocioException, EncontradoException {
        try {
            cargar(publicacion.getId());
            throw  new EncontradoException("se encuentra la publicacion con id="+publicacion.getId());
        } catch (NoEncontradoException e){        }
        try {
            return publicacionesDAO.save(publicacion);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new NegocioException(e);
        }
    }

    @Override
    public Publicacion cargar(long id) throws NegocioException, NoEncontradoException {
        Optional<Publicacion> o;
        try {
            o = publicacionesDAO.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new NegocioException(e);
        }
        if(!o.isPresent()){
            throw new NoEncontradoException("No se encuentra la publicacion con id="+id);
        }
        return o.get();
    }

    @Override
    public Publicacion modificar(Publicacion publicacion) throws NegocioException, NoEncontradoException {
        try {
            publicacionesDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new NegocioException(e);
        }
        cargar(publicacion.getId());
        try {
            return publicacionesDAO.save(publicacion);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new NegocioException(e);
        }
    }

    @Override
    public void eliminar(long id) throws NegocioException, NoEncontradoException {
        cargar(id);

        try {
            publicacionesDAO.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new NegocioException(e);
        }
    }

    @Override
    public List<Publicacion> listarNombres(String nombre) throws NegocioException {
        try {
            return publicacionesDAO.findByTitulo(nombre);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
    }
    /*
    @Override
    public List<Publicacion> listarLikes(int like) throws NegocioException {
        try {
            return publicacionesDAO.findByLikes();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
    }
    */
}
