package com.kcastillo.songmatch.repository;

import com.kcastillo.songmatch.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CancionRepository extends JpaRepository<Cancion, Long> {

    @Query(value = "SELECT  c.* FROM canciones c JOIN cantante ON cantante.id = c.cantante_id WHERE c.cantante_id=:artistId" , nativeQuery = true)
    List<Cancion> findSongByArtist(Long artistId);

    Optional<Cancion> findCancionByTituloIgnoreCase(String titulo);

}
