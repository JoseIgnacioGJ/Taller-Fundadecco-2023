package com.example.repositories;

import com.example.entities.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findAllByCalle(String calle);

    List<Cita> findAllByNumero(Integer numero);

    List<Cita> findAllByCalleAndNumero(String calle, Integer numero);

    List<Cita> findAllByCiudad(String ciudad);

    List<Cita> findAllByCp(Integer cp);

    List<Cita> findAllByCiudadAndCp(String ciudad, Integer cp);
}
