package com.prog3.TP1PersistenciaProg3Sacchetti.entidades;

import com.prog3.TP1PersistenciaProg3Sacchetti.util.TipoProducto;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto extends BaseEntidad{

    private TipoProducto tipo;

    private int tiempoEstimadoCocina;

    private String denominacion;

    private Double precioVenta;

    private Double precioCompra;

    private int stockActual;

    private int stockMinimo;

    private String unidadMedida;

    private String foto;

    private String receta;

}
