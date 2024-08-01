package org.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.tienda.entity.ActividadModel;
import org.tienda.exceptions.EntityAlreadyExistsException;
import org.tienda.exceptions.EntityNotFoundException;
import org.tienda.repository.ActividadRepository;

import java.util.List;
import java.util.Optional;

public class ActividadService implements IService<ActividadModel> {
    @Autowired
    private ActividadRepository actividadRepository;

    @Override
    public List<ActividadModel> findAll() {
        return actividadRepository.findAll();
    }

    @Override
    public ActividadModel findById(Long id) throws EntityNotFoundException {
        Optional<ActividadModel> actividadModel = actividadRepository.findById(id);

        if (actividadModel.isPresent()) {
            return actividadModel.get();
        } else {
            throw new EntityNotFoundException("Actividad", "id");
        }
    }

    @Override
    public ActividadModel save(ActividadModel actividadModel) throws EntityAlreadyExistsException {
        Optional<ActividadModel> actividadFindByAbreviatura = actividadRepository.findByAbreviatura(actividadModel.getAbreviatura());

        if (actividadFindByAbreviatura.isPresent()) {
            throw new EntityAlreadyExistsException("Actividad", "abreviatura");
        } else {
            return actividadRepository.save(actividadModel);
        }
    }

    @Override
    public ActividadModel update(ActividadModel actividadModel) throws EntityNotFoundException, EntityAlreadyExistsException {
        Optional<ActividadModel> actividadFindById = actividadRepository.findById(actividadModel.getId());
        if (actividadFindById.isEmpty()) {
            throw new EntityNotFoundException("Actividad", "id");
        }

        Optional<ActividadModel> actividadFindByAbreviatura = actividadRepository.findByAbreviatura(actividadModel.getAbreviatura());
        if (actividadFindByAbreviatura.isPresent()) {
            throw new EntityAlreadyExistsException("Actividad", "abreviatura");
        }

        return actividadRepository.save(actividadModel);
    }

    @Override
    public ActividadModel delete(Long id) throws EntityNotFoundException {
        Optional<ActividadModel> actividadModel = actividadRepository.findById(id);

        if (actividadModel.isPresent()) {
            actividadRepository.deleteById(id);
            return actividadModel.get();
        } else {
            throw new EntityNotFoundException("Actividad", "id");
        }
    }
}
