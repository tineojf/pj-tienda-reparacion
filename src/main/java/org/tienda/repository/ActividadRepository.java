package org.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tienda.entity.ActividadModel;

@Repository
public interface ActividadRepository extends JpaRepository<ActividadModel, Long> {
}
