package com.kcastillo.songmatch.repository;

import com.kcastillo.songmatch.model.Cantante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CantanteRepository extends JpaRepository<Cantante,Long> {

    @Query(value = "SELECT * FROM cantante", nativeQuery = true)
    List<Cantante> findAllArtist();

//    @Query(value = "INSERT INTO canciones (evaluacion,fecha_de_lanzamiento, genero, titulo) values (8.6,'2007-12-12','BALADA','La magaleña')", nativeQuery = true)
//    void insertSong();

    Optional<Cantante> findByNameContainsIgnoreCase(String text);

}
