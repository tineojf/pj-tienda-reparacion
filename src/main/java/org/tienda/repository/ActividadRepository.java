package org.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tienda.entity.ActividadModel;

import java.util.Optional;

@Repository
public interface ActividadRepository extends JpaRepository<ActividadModel, Long> {

    Optional<ActividadModel> findByAbreviatura(String abreviatura);
}
