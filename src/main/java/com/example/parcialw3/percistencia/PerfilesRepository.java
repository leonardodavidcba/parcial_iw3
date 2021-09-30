package com.example.parcialw3.percistencia;

import com.example.parcialw3.modelo.Perfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PerfilesRepository extends JpaRepository<Perfiles, Long> {
    @Transactional(readOnly = true)  // especifique que este metodo es solo para consultar los datos  ... puede ir o no
    List<Perfiles> findByNombreUsuario(@Param("nombreUsuario") String usuario);    //  @Param("nombre") puede ir o no, en las versiones antiguas era necesario

    @Transactional(readOnly = true)  // especifique que este metodo es solo para consultar los datos  ... puede ir o no
    List<Perfiles> findByDireccion(@Param("nombreUsuario") String direccion);    //  @Param("nombre") puede ir o no, en las versiones antiguas era necesario
}
