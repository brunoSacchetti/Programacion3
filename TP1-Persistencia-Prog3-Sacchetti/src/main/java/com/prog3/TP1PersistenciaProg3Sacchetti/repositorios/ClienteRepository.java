package com.prog3.TP1PersistenciaProg3Sacchetti.repositorios;

import com.prog3.TP1PersistenciaProg3Sacchetti.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
