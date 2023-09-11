package com.prog3.TP1PersistenciaProg3Sacchetti.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Domicilio extends BaseEntidad{

    private String calle;

    private String numero;

    private String localidad;

    @OneToMany(orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id_domicilio") //en pedidos existira una fk llamada pedido_id que apunta a usuario
    @Builder.Default
    private List<Pedido> pedidosDomicilio = new ArrayList<>();


    // RELACION DOMICILIO - CLIENTE MANY TO ONE UNI
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id") //en pedidos existira una fk llamada pedido_id que apunta a usuario
    //@Builder.Default TIRA ERROR @BUILDER LINE 17
    private Cliente cliente;


    //METODO PARA AGREGAR PEDIDOS
    public void agregarPedidoDomicilio(Pedido p){

        pedidosDomicilio.add(p);
    }

    //METODO PARA MOSTRAR PEDIDOS
    public void mostrarPedidosDomicilio() {
        System.out.println("Pedidos de la calle: " + calle + " " + numero);
        for (Pedido pedido : pedidosDomicilio) {
            System.out.println("Fecha: " + pedido.getFecha() + ", Total: " + pedido.getTotal());
        }
    }

}
