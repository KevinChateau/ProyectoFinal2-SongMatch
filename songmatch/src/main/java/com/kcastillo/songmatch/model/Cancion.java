package com.kcastillo.songmatch.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "canciones")
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private LocalDate fechaDeLanzamiento;
    private double evaluacion;
    @ManyToOne
    private Cantante cantante;

    public Cancion() {
    }

    public Cancion(String titulo, Genero genero, LocalDate fechaDeLanzamiento, double evaluacion) {
        this.titulo = titulo;
        this.genero = genero;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
        this.evaluacion = evaluacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public LocalDate getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public void setFechaDeLanzamiento(LocalDate fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public Cantante getCantante() {
        return cantante;
    }

    public void setCantante(Cantante cantante) {
        this.cantante = cantante;
    }

    public double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(double evaluacion) {
        this.evaluacion = evaluacion;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", genero=" + genero +
                ", fechaDeLanzamiento=" + fechaDeLanzamiento +
                ", evaluacion=" + evaluacion +
                ", cantante=" + cantante +
                '}';
    }
}
