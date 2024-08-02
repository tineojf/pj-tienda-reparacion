# Proyecto Tienda de Accesorios - Java Test Assignment

El proyecto fue hecho en IntelliJ IDEA, con Spring Boot, MySQL, Spring Data JPA, Spring Web, Lombok, HTML, CSS,
JavaScript. Es recomendable abrir la carpeta en ese IDE y cumplir los **Requerimientos** para poder ejecutar el
proyecto.

## Funcionalidades

- Crear una persona
- Eliminar una persona
- Listar personas
- Actualizar una persona

## Tecnologías

- Java - Spring Boot
- MYSQL
- Spring Data JPA
- Spring Web
- Lombok
- HTML, CSS, JavaScript

## Requerimientos

1. IntelliJ IDEA

    - Se puede descargar desde el siguiente enlace: https://www.jetbrains.com/idea/download/
    - Abrir la carpeta del proyecto en IntelliJ IDEA.

2. MySQL

    - Se puede modificar el archivo `application.properties` para cambiar la configuración de la base de datos.
    - En caso tener otro puerto, usuario o contraseña, se debe modificar el archivo `application.properties` en la
      carpeta `src/main/resources`.
    - En caso tener Docker, se puede correr el siguiente comando para levantar una base de datos:
      ```bash
      docker run -d -p 9000:3306 -e MYSQL_ROOT_PASSWORD=root MYSQL_DATABASE=dbtienda --name dbtienda mysql
      ```

## Levantar el proyecto

- Ejecutar el archivo `TiendaApplication.java` en la carpeta `src/main/java/org/tienda`.
- O ejecutar el siguiente comando en la terminal:
  ```bash
  mvn spring-boot:run
  ```

## Endpoints

- Frontend http://localhost:8080/
- API - GET http://localhost:8080/personas
- API - POST http://localhost:8080/personas
- API - PUT http://localhost:8080/personas/{id}
- API - DELETE http://localhost:8080/personas/{id}
