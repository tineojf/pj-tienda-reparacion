package org.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.tienda.entity.ActividadModel;
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
    public ActividadModel findById(Long id) {
        Optional<ActividadModel> actividadModel = actividadRepository.findById(id);

        if (actividadModel.isPresent()) {
            return actividadModel.get();
        } else {
            // notfound
            return null;
        }
    }

    @Override
    public ActividadModel save(ActividadModel actividadModel) {
        Optional<ActividadModel> actividadFindByAbreviatura = actividadRepository.findByAbreviatura(actividadModel.getAbreviatura());

        if (actividadFindByAbreviatura.isPresent()) {
            // already exists
            return null;
        } else {
            return actividadRepository.save(actividadModel);
        }
    }

    @Override
    public ActividadModel update(ActividadModel actividadModel) {
        Optional<ActividadModel> actividadFindByAbreviatura = actividadRepository.findByAbreviatura(actividadModel.getAbreviatura());

        if (actividadFindByAbreviatura.isPresent()) {
            return actividadRepository.save(actividadModel);
        } else {
            // notfound
            return null;
        }
    }

    @Override
    public ActividadModel delete(Long id) {
        Optional<ActividadModel> actividadModel = actividadRepository.findById(id);

        if (actividadModel.isPresent()) {
            actividadRepository.deleteById(id);
            return actividadModel.get();
        } else {
            // notfound
            return null;
        }
    }
}
