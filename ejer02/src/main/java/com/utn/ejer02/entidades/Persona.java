package com.utn.ejer02.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Persona implements Serializable {
    @Id //jakarta.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //(primary key) para cada clase colocamos un id para las tablas ed base de dato

    private String nombre;

    private String apellido;

    private int edad;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) // se da de alta o elimina todo lo que esta por detras, como persona, domicilio, etc.
    @JoinColumn(name = "domicilio_id") //join column clave foranea estara dentro de la tabla de persona, pero con nombre domicilio id
    private Domicilio domicilio;

}
