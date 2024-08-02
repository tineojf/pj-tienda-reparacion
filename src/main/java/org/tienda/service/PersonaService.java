package org.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tienda.entity.PersonaModel;
import org.tienda.exceptions.EntityAlreadyExistsException;
import org.tienda.exceptions.EntityNotFoundException;
import org.tienda.repository.PersonaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService implements IService<PersonaModel> {
    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<PersonaModel> findAll() {
        return personaRepository.findAll();
    }

    @Override
    public PersonaModel findById(Long id) throws EntityNotFoundException {
        Optional<PersonaModel> personaModel = personaRepository.findById(id);

        if (personaModel.isPresent()) {
            return personaModel.get();
        } else {
            throw new EntityNotFoundException("Persona", "id");
        }
    }

    @Override
    public PersonaModel save(PersonaModel personaModel) throws EntityAlreadyExistsException {
        Optional<PersonaModel> personaEqualsByNombre = personaRepository.findByNombre(personaModel.getNombre());
        Optional<PersonaModel> personaEqualsByApellido = personaRepository.findByApellido(personaModel.getApellido());

        if (personaEqualsByNombre.isPresent() || personaEqualsByApellido.isPresent()) {
            throw new EntityAlreadyExistsException("Persona", "nombre o apellido");
        } else {
            return personaRepository.save(personaModel);
        }
    }

    @Override
    public PersonaModel update(PersonaModel personaModel) throws EntityNotFoundException, EntityAlreadyExistsException {
        Optional<PersonaModel> personaFindById = personaRepository.findById(personaModel.getId());
        if (personaFindById.isEmpty()) {
            throw new EntityNotFoundException("Persona", "id");
        }

        Optional<PersonaModel> personaFindByNombre = personaRepository.findByNombre(personaModel.getNombre());
        Optional<PersonaModel> personaFindByApellido = personaRepository.findByApellido(personaModel.getApellido());
        if (personaFindByNombre.isPresent() || personaFindByApellido.isPresent()) {
            throw new EntityAlreadyExistsException("Persona", "nombre o apellido");
        }

        return personaRepository.save(personaModel);
    }

    @Override
    public PersonaModel delete(Long id) throws EntityNotFoundException {
        Optional<PersonaModel> personaFindById = personaRepository.findById(id);

        if (personaFindById.isPresent()) {
            personaRepository.deleteById(id);
            return personaFindById.get();
        } else {
            throw new EntityNotFoundException("Persona", "id");
        }
    }

    public List<PersonaModel> findByNameRegEx(String param) {
        return personaRepository.findByNameRegEx(param);
    }
}
