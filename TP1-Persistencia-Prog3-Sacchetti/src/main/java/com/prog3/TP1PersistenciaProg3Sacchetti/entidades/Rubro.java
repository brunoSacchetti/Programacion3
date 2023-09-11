package com.prog3.TP1PersistenciaProg3Sacchetti.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rubro extends BaseEntidad {

    private String denominacion;

    @OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_rubro_id") //en pedidos existira una fk llamada pedido_id que apunta a rubro
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    //METODO PARA AGREGAR PEDIDOS
    public void agregarProducto(Producto p){
        productos.add(p);
    }

    public void mostrarRubro() {
        System.out.println("Denominacion: " + denominacion);
        for (Producto producto : productos) {
            System.out.println("Tipo: " + producto.getTipo() + ", Stock Actual: " + producto.getStockActual());
        }
    }

}
