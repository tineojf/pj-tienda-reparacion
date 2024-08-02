package org.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tienda.entity.PersonaModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaModel, Long> {
    Optional<PersonaModel> findByNombre(String name);

    Optional<PersonaModel> findByApellido(String apellido);

    @Query("SELECT o FROM persona o WHERE LOWER(CONCAT(o.apellido, ' ', o.nombre)) LIKE LOWER(CONCAT('%', :param, '%'))")
    List<PersonaModel> findByNameRegEx(@Param("param") String param);
}
