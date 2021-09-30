package com.example.parcialw3.negocio.perfiles;

import com.example.parcialw3.modelo.Perfiles;
import com.example.parcialw3.negocio.exceptions.EncontradoException;
import com.example.parcialw3.negocio.exceptions.NegocioException;
import com.example.parcialw3.negocio.exceptions.NoEncontradoException;
import com.example.parcialw3.percistencia.PerfilesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilesNegocio implements IPerfilesNegocio{
    private Logger log = LoggerFactory.getLogger(PerfilesNegocio.class);


    @Autowired
    private PerfilesRepository perfilesDAO;

    @Override
    public List<Perfiles> listado() throws NegocioException {
        try {
            return perfilesDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new NegocioException(e);
        }
    }

    @Override
    public Perfiles agregar(Perfiles perfiles) throws NegocioException, EncontradoException {
        try {
            cargar(perfiles.getId());
            throw  new EncontradoException("se encuentra el perfil con id="+perfiles.getId());
        } catch (NoEncontradoException e){        }
        try {
            return perfilesDAO.save(perfiles);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new NegocioException(e);
        }
    }

    @Override
    public Perfiles cargar(long id) throws NegocioException, NoEncontradoException {
        Optional<Perfiles> o;
        try {
            o = perfilesDAO.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new NegocioException(e);
        }
        if(!o.isPresent()){
            throw new NoEncontradoException("No se encuentra el perfil con id="+id);
        }
        return o.get();
    }

    @Override
    public Perfiles modificar(Perfiles perfiles) throws NegocioException, NoEncontradoException {
        try {
            perfilesDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new NegocioException(e);
        }
        cargar(perfiles.getId());
        try {
            return perfilesDAO.save(perfiles);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new NegocioException(e);
        }
    }

    @Override
    public void eliminar(long id) throws NegocioException, NoEncontradoException {
        cargar(id);

        try {
            perfilesDAO.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new NegocioException(e);
        }
    }

    @Override
    public List<Perfiles> listarNombres(String nombre) throws NegocioException {
        try {
            return perfilesDAO.findByNombreUsuario(nombre);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
    }

    @Override
    public List<Perfiles> listarDireccion(String direccion) throws NegocioException {
        try {
            return perfilesDAO.findByDireccion(direccion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
    }
}
