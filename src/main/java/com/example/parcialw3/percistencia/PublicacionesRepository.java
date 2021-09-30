package com.example.parcialw3.percistencia;

import com.example.parcialw3.modelo.Perfiles;
import com.example.parcialw3.modelo.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface PublicacionesRepository extends JpaRepository<Publicacion, Long> {
    @Transactional(readOnly = true)  // especifique que este metodo es solo para consultar los datos  ... puede ir o no
    List<Publicacion> findByTitulo(@Param("titulo") String titulo);    //  @Param("titulo") puede ir o no, en las versiones antiguas era necesario

    /*
    @Query(value = "SELECT  likes FROM publicaciones ORDER BY likes desc limit 1")
    default List<Publicacion> findByLikes() {

    }
    */
}
