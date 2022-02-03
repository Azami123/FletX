package com.sistema.peliculas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.peliculas.modelo.Pelicula;

public interface PeliculaRepositorio extends JpaRepository<Pelicula,Integer> {

}
