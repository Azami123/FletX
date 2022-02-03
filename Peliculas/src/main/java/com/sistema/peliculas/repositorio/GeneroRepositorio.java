package com.sistema.peliculas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.peliculas.modelo.Genero;

public interface GeneroRepositorio extends JpaRepository<Genero,Integer> {

}
