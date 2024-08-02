package org.tienda;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tienda.repository.DBInitializer;

@SpringBootApplication
public class TiendaApplication {
    @Autowired
    private DBInitializer dbInitializer;

    public static void main(String[] args) {
        SpringApplication.run(TiendaApplication.class, args);
    }

    @PostConstruct
    public void loadDataToORM() throws Exception {
        dbInitializer.loadData();
    }
}
