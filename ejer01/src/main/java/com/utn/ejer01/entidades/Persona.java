package com.utn.ejer01.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity //Califica  la proxima instruccion, sera manejada por JPA (motor de persistencia)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona implements Serializable {

    @Id //jakarta.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //(primary key) para cada clase colocamos un id para las tablas ed base de dato

    private String nombre;

    private String apellido;

    private int edad;


}
