package com.samuel.reservasServidorGraphql.resolver;

import java.util.List;

import com.samuel.reservasServidorGraphql.model.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.samuel.reservasServidorGraphql.model.Usuario;
import com.samuel.reservasServidorGraphql.service.UsuarioService;

@Component
public class Query implements GraphQLQueryResolver {
	
	@Autowired
    private UsuarioService service;

		
	public List<Usuario> characters() {
		return service.getCharacters();
	}
	
	public Usuario character(Integer id) {
		return service.getCharacter(id);
	}
	
	public List<Restaurante> allSeries() {
		return service.getSeries();
	}
	
	public Restaurante series(Integer id) {
		return service.getSeries(id);
	}	
	
}