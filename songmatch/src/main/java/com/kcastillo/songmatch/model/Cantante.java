package com.kcastillo.songmatch.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cantante")
public class Cantante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private Integer listeners;
    @OneToMany(mappedBy = "cantante", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cancion> canciones;

    public Cantante() {
    }

    public Cantante(String name, Genero genero, Integer listeners) {
        this.name = name;
        this.genero = genero;
        this.listeners = listeners;
    }

    public Cantante(String name, Genero genero, Integer listeners, List<Cancion> canciones) {
        this.name = name;
        this.genero = genero;
        this.listeners = listeners;
        this.canciones = canciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Integer getListeners() {
        return listeners;
    }

    public void setListeners(Integer listeners) {
        this.listeners = listeners;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        canciones.forEach(c -> c.setCantante(this));
        this.canciones = canciones;
    }


    @Override
    public String toString() {
        return "Cantante{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genero=" + genero +
                ", listeners=" + listeners;
    }
}
