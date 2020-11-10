package com.samuel.reservasServidorGraphql.dao;

import com.samuel.reservasServidorGraphql.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteDao extends JpaRepository<Restaurante, Integer> {
	
}
