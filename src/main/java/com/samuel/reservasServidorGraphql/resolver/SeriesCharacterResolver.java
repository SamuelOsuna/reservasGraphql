package com.samuel.reservasServidorGraphql.resolver;

import com.samuel.reservasServidorGraphql.model.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.samuel.reservasServidorGraphql.model.Usuario;
import com.samuel.reservasServidorGraphql.service.UsuarioService;

@Component
public class SeriesCharacterResolver implements GraphQLResolver<Usuario> {
	
	@Autowired
    private UsuarioService usuarioService;

    public Restaurante getSeries(Usuario character) {
    	return usuarioService.getSeries(character.getSeries().getId());
    }
        
}
