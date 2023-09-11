package com.prog3.TP1PersistenciaProg3Sacchetti.repositorios;

import com.prog3.TP1PersistenciaProg3Sacchetti.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}
