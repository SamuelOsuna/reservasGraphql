package com.samuel.reservasServidorGraphql.dao;

import com.samuel.reservasServidorGraphql.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Integer> {
	
}
