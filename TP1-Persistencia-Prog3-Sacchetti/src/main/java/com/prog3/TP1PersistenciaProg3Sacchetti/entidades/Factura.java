package com.prog3.TP1PersistenciaProg3Sacchetti.entidades;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.prog3.TP1PersistenciaProg3Sacchetti.util.FormaPago;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura extends BaseEntidad {

    private String fecha;

    private int numero;

    private Double descuento;

    private FormaPago formaPago;

    private int total;


}
