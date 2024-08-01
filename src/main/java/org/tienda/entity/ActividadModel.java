package org.tienda.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "actividad")
public class ActividadModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String abreviatura;

    @Column(nullable = false)
    private String descripcion;

    @ManyToMany(mappedBy = "actividades")
    private Set<PersonaModel> personas;

    // Constructor
    public ActividadModel(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
