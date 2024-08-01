package org.tienda.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String apellido;

    private String celular;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String genero;

    @ManyToMany
    @JoinTable(
            name = "persona_actividad",
            joinColumns = @JoinColumn(name = "persona_id"),
            inverseJoinColumns = @JoinColumn(name = "actividad_id")
    )
    @JsonIgnore
    private Set<Actividad> actividades;

    // Constructor
    public Persona(String nombre, String apellido, String celular, String genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", celular='" + celular + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}
