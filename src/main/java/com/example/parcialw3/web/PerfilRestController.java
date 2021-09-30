package com.example.parcialw3.web;

import com.example.parcialw3.modelo.Perfiles;
import com.example.parcialw3.negocio.exceptions.EncontradoException;
import com.example.parcialw3.negocio.exceptions.NegocioException;
import com.example.parcialw3.negocio.exceptions.NoEncontradoException;
import com.example.parcialw3.negocio.perfiles.IPerfilesNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PerfilRestController {

    @Autowired
    private IPerfilesNegocio perfilesNegocio;


    @GetMapping(value = "/perfiles")
    public ResponseEntity<List<Perfiles>> listado(
            // filtrado por nombre y direccion
            @RequestParam(name = "direccion", required = false, defaultValue = "*") String direccion,
            @RequestParam(name = "nombre", required = false, defaultValue = "*") String nombre

         )
    {
        try {
            if (!nombre.equals("*")) return new ResponseEntity<List<Perfiles>>(perfilesNegocio.listarNombres(nombre), HttpStatus.OK);
            if (!direccion.equals("*")) return new ResponseEntity<List<Perfiles>>(perfilesNegocio.listarDireccion(direccion), HttpStatus.OK);
            return new ResponseEntity<List<Perfiles>>(perfilesNegocio.listado(), HttpStatus.OK);
        } catch (NegocioException e) {
            return new ResponseEntity<List<Perfiles>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/perfiles/{id}")
    public ResponseEntity<Perfiles> cargar(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<Perfiles>(perfilesNegocio.cargar(id), HttpStatus.OK);
        } catch (NegocioException | NoEncontradoException e) {
            return new ResponseEntity<Perfiles>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/perfiles")
    public ResponseEntity<String> agregar(@RequestBody Perfiles perfiles) {
        try {
            Perfiles respuesta = perfilesNegocio.agregar(perfiles);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("location","/perfiles/"+respuesta.getId());
            return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
        } catch (NegocioException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (EncontradoException | NoEncontradoException e) {
            return new ResponseEntity<String>(HttpStatus.FOUND);
        }
    }

    @PutMapping(value = "/perfiles")
    public ResponseEntity<String> modificar(@RequestBody Perfiles perfil) {
        try {
            perfilesNegocio.modificar(perfil);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (NegocioException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoEncontradoException e) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/perfiles/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") long id) {
        try {
            perfilesNegocio.eliminar(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (NegocioException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoEncontradoException e) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

}
