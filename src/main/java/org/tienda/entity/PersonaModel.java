package org.tienda.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persona")
public class PersonaModel {
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

    private String tutor;

    private Boolean pFormateo;
    private Boolean pLimpieza;
    private Boolean pDesinfeccion;
    private Boolean pDiagnosticar;
    private Boolean pCambios;

    private Boolean nFormateo;
    private Boolean nLimpieza;
    private Boolean nDesinfeccion;
    private Boolean nDiagnosticar;
    private Boolean nCambios;

    private Boolean cFlasheo;
    private Boolean cBateria;
    private Boolean cPantalla;
    private Boolean cVidrio;

    private Boolean aRecibir;
    private Boolean aPresupuestar;
    private Boolean aVenta;
    private Boolean aCompra;

    // Constructor
    public PersonaModel(String nombre, String apellido, String celular, String genero, String tutor, Boolean pFormateo, Boolean pLimpieza, Boolean pDesinfeccion, Boolean pDiagnosticar, Boolean pCambios, Boolean nFormateo, Boolean nLimpieza, Boolean nDesinfeccion, Boolean nDiagnosticar, Boolean nCambios, Boolean cFlasheo, Boolean cBateria, Boolean cPantalla, Boolean cVidrio, Boolean aRecibir, Boolean aPresupuestar, Boolean aVenta, Boolean aCompra) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.genero = genero;
        this.tutor = tutor;
        this.pFormateo = pFormateo;
        this.pLimpieza = pLimpieza;
        this.pDesinfeccion = pDesinfeccion;
        this.pDiagnosticar = pDiagnosticar;
        this.pCambios = pCambios;
        this.nFormateo = nFormateo;
        this.nLimpieza = nLimpieza;
        this.nDesinfeccion = nDesinfeccion;
        this.nDiagnosticar = nDiagnosticar;
        this.nCambios = nCambios;
        this.cFlasheo = cFlasheo;
        this.cBateria = cBateria;
        this.cPantalla = cPantalla;
        this.cVidrio = cVidrio;
        this.aRecibir = aRecibir;
        this.aPresupuestar = aPresupuestar;
        this.aVenta = aVenta;
        this.aCompra = aCompra;
    }


    // To string
    @Override
    public String toString() {
        return "PersonaModel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", celular='" + celular + '\'' +
                ", genero='" + genero + '\'' +
                ", tutor='" + tutor + '\'' +
                ", pFormateo=" + pFormateo +
                ", pLimpieza=" + pLimpieza +
                ", pDesinfeccion=" + pDesinfeccion +
                ", pDiagnosticar=" + pDiagnosticar +
                ", pCambios=" + pCambios +
                ", nFormateo=" + nFormateo +
                ", nLimpieza=" + nLimpieza +
                ", nDesinfeccion=" + nDesinfeccion +
                ", nDiagnosticar=" + nDiagnosticar +
                ", nCambios=" + nCambios +
                ", cFlasheo=" + cFlasheo +
                ", cBateria=" + cBateria +
                ", cPantalla=" + cPantalla +
                ", cVidrio=" + cVidrio +
                ", aRecibir=" + aRecibir +
                ", aPresupuestar=" + aPresupuestar +
                ", aVenta=" + aVenta +
                ", aCompra=" + aCompra +
                '}';
    }
}
