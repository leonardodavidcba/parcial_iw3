package com.example.parcialw3.web;

import com.example.parcialw3.modelo.Publicacion;
import com.example.parcialw3.negocio.exceptions.EncontradoException;
import com.example.parcialw3.negocio.exceptions.NegocioException;
import com.example.parcialw3.negocio.exceptions.NoEncontradoException;
import com.example.parcialw3.negocio.publicaciones.IPublicacionesNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublicacionRestController {

    @Autowired
    private IPublicacionesNegocio publicacionNegocio;


    @GetMapping(value = "/publicacion")
    public ResponseEntity<List<Publicacion>> listado()
    {
        try {
            return new ResponseEntity<List<Publicacion>>(publicacionNegocio.listado(), HttpStatus.OK);
        } catch (NegocioException e) {
            return new ResponseEntity<List<Publicacion>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/publicacion/{id}")
    public ResponseEntity<Publicacion> cargar(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<Publicacion>(publicacionNegocio.cargar(id), HttpStatus.OK);
        } catch (NegocioException | NoEncontradoException e) {
            return new ResponseEntity<Publicacion>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/publicacion")
    public ResponseEntity<String> agregar(@RequestBody Publicacion publicacion) {
        try {
            Publicacion respuesta = publicacionNegocio.agregar(publicacion);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("location","/publicaciones/"+respuesta.getId());
            return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
        } catch (NegocioException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (EncontradoException | NoEncontradoException e) {
            return new ResponseEntity<String>(HttpStatus.FOUND);
        }
    }

    @PutMapping(value = "/publicacion")
    public ResponseEntity<String> modificar(@RequestBody Publicacion publicacion) {
        try {
            publicacionNegocio.modificar(publicacion);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (NegocioException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoEncontradoException e) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/publicacion/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") long id) {
        try {
            publicacionNegocio.eliminar(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (NegocioException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoEncontradoException e) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

}
