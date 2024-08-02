package org.tienda.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tienda.entity.PersonaModel;
import org.tienda.service.PersonaService;

@Component
public class DBInitializer {
    @Autowired
    private PersonaService personaService;

    public void loadData() throws Exception {
        PersonaModel p1 = new PersonaModel("Jean Franco", "Tineo", "+51 999 888 777", "M", null, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true);
        PersonaModel p2 = new PersonaModel("Juan", "Perez", "+51 999 888 777", "M", null, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, false);
        PersonaModel p3 = new PersonaModel("Maria", "Gomez", "+51 999 888 777", "F", null, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false);

        personaService.save(p1);
        personaService.save(p2);
        personaService.save(p3);
    }
}
