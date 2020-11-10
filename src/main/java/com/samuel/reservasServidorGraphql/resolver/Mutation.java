package com.samuel.reservasServidorGraphql.resolver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.samuel.reservasServidorGraphql.model.Restaurante;
import com.samuel.reservasServidorGraphql.model.Usuario;
import com.samuel.reservasServidorGraphql.service.UsuarioService;

@Component
public class Mutation implements GraphQLMutationResolver {
    
	@Autowired
    private UsuarioService service;
	
	
	public Restaurante createSeries(String name, Integer nrOfSeasons) {
		return service.createSeries(name, nrOfSeasons);
	}
	
	public Usuario createCharacter(String name, String nickname, String occupation, String birthday, Integer seriesId) {
		LocalDate dayOfBirth = LocalDate.parse(birthday, DateTimeFormatter.ISO_DATE);  // date pattern example: '2011-12-03'
		return service.createCharacter(name, nickname, occupation, dayOfBirth, seriesId);
	}
	    
}