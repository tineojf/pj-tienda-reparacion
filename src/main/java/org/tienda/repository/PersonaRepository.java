package org.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tienda.entity.PersonaModel;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaModel, Long> {
}
