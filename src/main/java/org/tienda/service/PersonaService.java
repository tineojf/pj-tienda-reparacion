package org.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tienda.entity.PersonaModel;
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
    public PersonaModel findById(Long id) {
        Optional<PersonaModel> personaModel = personaRepository.findById(id);

        if (personaModel.isPresent()) {
            return personaModel.get();
        } else {
            // notfound
            return null;
        }
    }

    @Override
    public PersonaModel save(PersonaModel personaModel) {
        Optional<PersonaModel> personaEqualsByNombre = personaRepository.findByNombre(personaModel.getNombre());
        Optional<PersonaModel> personaEqualsByApellido = personaRepository.findByApellido(personaModel.getApellido());

        if (personaEqualsByNombre.isPresent() || personaEqualsByApellido.isPresent()) {
            // already exists
            return null;
        } else {
            return personaRepository.save(personaModel);
        }
    }

    @Override
    public PersonaModel update(PersonaModel personaModel) {
        Optional<PersonaModel> personaFindById = personaRepository.findById(personaModel.getId());

        if (personaFindById.isPresent()) {
            return personaRepository.save(personaModel);
        } else {
            // notfound
            return null;
        }
    }

    @Override
    public PersonaModel delete(Long id) {
        Optional<PersonaModel> personaFindById = personaRepository.findById(id);

        if (personaFindById.isPresent()) {
            personaRepository.deleteById(id);
            return personaFindById.get();
        } else {
            // notfound
            return null;
        }
    }
}
