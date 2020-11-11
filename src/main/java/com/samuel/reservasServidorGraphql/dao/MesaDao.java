package com.samuel.reservasServidorGraphql.dao;

import com.samuel.reservasServidorGraphql.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaDao extends JpaRepository<Mesa, Integer>{
}
