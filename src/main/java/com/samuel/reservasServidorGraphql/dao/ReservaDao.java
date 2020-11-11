package com.samuel.reservasServidorGraphql.dao;

import com.samuel.reservasServidorGraphql.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaDao extends JpaRepository<Reserva, Integer>{
}
