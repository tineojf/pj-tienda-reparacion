package org.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tienda.entity.PersonaModel;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaModel, Long> {

    Optional<PersonaModel> findByNombre(String name);

    Optional<PersonaModel> findByApellido(String apellido);
}
