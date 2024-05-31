package com.kcastillo.songmatch.principal;

import com.kcastillo.songmatch.model.Cancion;
import com.kcastillo.songmatch.model.Cantante;
import com.kcastillo.songmatch.model.Genero;
import com.kcastillo.songmatch.repository.CancionRepository;
import com.kcastillo.songmatch.repository.CantanteRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private CantanteRepository repositorio;
    private CancionRepository repositorioCancion;
    private List<Cantante> cantantes; //To save all artist of users
    private List<Cancion> canciones = new ArrayList<>(); //To save all songs of users
    private Optional<Cantante> cantanteBuscado;

    public Principal(CantanteRepository repositorio,CancionRepository repositorioCantante) {
        this.repositorio = repositorio;
        this.repositorioCancion = repositorioCantante;
    }

    public void showMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Registrar datos de cantantes
                    2 - Registrar datos de canciones
                    3 - Buscar cantante por nombre
                    4 - Buscar canciones de un cantante
                    5 - Mostrar todos los cantantes
                    6 - Actualizar canción
                    7 - Eliminar canción por nombre
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    registerArtist();
                    break;
                case 2:
                    registerSong();
                    break;
                case 3:
                    findArtist();
                    break;
                case 4:
                    findSongByArtist();
                    break;
                case 5:
                    showAllArtist();
                    break;
                case 6:
                    updateSong();
                    break;
                case 7:
                    deleteSongByName();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void registerArtist() {
        System.out.println("Ingrese el nombre del artista a agregar: ");
        var artistName = teclado.nextLine();
        System.out.println("Ingrese el género: ");
        var genero = Genero.fromString(teclado.nextLine());

        Cantante cantante = new Cantante(artistName,genero,1000);
        repositorio.save(cantante);
    }
    private void registerSong() {
        System.out.println("Ingrese el nombre de la canción a agregar: ");
        var songName = teclado.nextLine();
        System.out.println("Ingrese el género: ");
        var genero = Genero.fromString(teclado.nextLine());
        System.out.println("Ingrese su evaluación [0.0-10.0]: ");
        var evaluation = Double.valueOf(teclado.nextLine());
        System.out.println("Ingrese la fecha de lanzamiento: ");
        var releasedSong = LocalDate.of(2022, 5, 19);

        findArtist();

        Cancion myson = new Cancion(songName,genero,releasedSong,evaluation);
            /*Cantante miCantante = new Cantante(cantante.get().getName(), cantante.get().getGenero(),
                    cantante.get().getListeners());
            miCantante.setCanciones(canciones);
            repositorio.save(miCantante);*/

            myson.setCantante(cantanteBuscado.get());
            canciones.add(myson);
            repositorioCancion.save(myson);

    }


    private void findArtist() {
        System.out.println("Ingrese el nombre del artista a buscar: ");
        var artist = teclado.nextLine();

        cantanteBuscado = repositorio.findByNameContainsIgnoreCase(artist);

        if(cantanteBuscado.isPresent()){
            System.out.println("Cantante encontrado: ");
            System.out.println(cantanteBuscado.get());
        }else {
            System.out.println("Cantante no encontrado");
        }

    }
    private void findSongByArtist() {
        findArtist();
//        List<Cancion> canciones = repositorioCantante.findSongByArtist(cantanteBuscado.get().getId());
        List<Cancion> canciones = repositorioCancion.findSongByArtist(cantanteBuscado.get().getId());
        System.out.println("Canciones del artista " + cantanteBuscado.get().getName() + ":");
        canciones.forEach(System.out::println);
    }

    private void showAllArtist(){
        cantantes = repositorio.findAllArtist();
        System.out.println("******** Lista de artistas registrados: ********");
//        cantantes.forEach(c -> System.out.println(c.getId() + ":" + c.getName() + " - " + c.getGenero()));
        cantantes.forEach(System.out::println);
    }

    private void updateSong() {
        System.out.println("Ingrese el nombre de la canción a agregar: ");
        var songName = teclado.nextLine();
        Optional<Cancion> mySong = repositorioCancion.findCancionByTituloIgnoreCase(songName);

        if(mySong.isPresent()){
            System.out.println("Canción encontrada " + mySong.get());
            System.out.println("Ingrese el género " + "[Valor actual:" + mySong.get().getGenero() +"]:");
            var genero = Genero.fromString(teclado.nextLine());
            System.out.println("Ingrese su evaluación [0.0-10.0]: "+ "[Valor actual:" + mySong.get().getEvaluacion() +"]:");
            var evaluation = Double.valueOf(teclado.nextLine());
//            System.out.println("Ingrese la fecha de lanzamiento: ");
            var releasedSong = LocalDate.of(2022, 5, 19);

            findArtist();
            Cancion myNewSon = new Cancion(songName,genero,releasedSong,evaluation);
            myNewSon.setId(mySong.get().getId());
            myNewSon.setCantante(cantanteBuscado.get());
            canciones.add(myNewSon);
            repositorioCancion.save(myNewSon);


        }else {
            System.out.println("Canción no encontrada");
        }

    }

    private void deleteSongByName() {
        System.out.println("Ingrese el nombre de la canción a eliminar: ");
        var songName = teclado.nextLine();
        Optional<Cancion> mySong = repositorioCancion.findCancionByTituloIgnoreCase(songName);

        if (mySong.isPresent()) {
            repositorioCancion.delete(mySong.get());
        } else {
            System.out.println("Canción no encontrada");
        }
    }


}
