package com.samuel.reservasServidorGraphql.resolver;

import java.util.List;
import java.util.Set;

import com.samuel.reservasServidorGraphql.model.Mesa;
import com.samuel.reservasServidorGraphql.model.Reserva;
import com.samuel.reservasServidorGraphql.model.Restaurante;
import com.samuel.reservasServidorGraphql.service.MesaService;
import com.samuel.reservasServidorGraphql.service.ReservaService;
import com.samuel.reservasServidorGraphql.service.RestauranteService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.samuel.reservasServidorGraphql.model.Usuario;
import com.samuel.reservasServidorGraphql.service.UsuarioService;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
public class Query implements GraphQLQueryResolver {
	
	@Autowired
    private RestauranteService restauranteService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ReservaService reservaService;

	@Autowired
	private MesaService mesaService;

	//Métodos de Restaurante
	public List<Restaurante> restaurantes(){
		return restauranteService.getRestaurantes();
	}

	public Restaurante restaurante(int id) throws NotFoundException {
		return restauranteService.restaurantePorId(id);
	}

	//Métodos de Usuario
	public List<Usuario> usuariosPorNombre(String nombre) throws NotFoundException{
		return usuarioService.usuariosPorNombre(nombre);
	}

	public Usuario usuario(int id){
		return usuarioService.usuarioPorId(id);
	}

	//Métodos de Reserva
	public List<Reserva> reservasPorUsuario(int id_usuario) throws NotFoundException {
		return reservaService.reservasPorUsuario(id_usuario);
	}

	public Set<Reserva> reservasPorRestaurante(int id_restaurante) throws NotFoundException {
		return reservaService.reservasPorRestaurante(id_restaurante);
	}

	//Métodos de Mesa
	public List<Mesa> mesasPorRestaurante(int id_restaurante) throws NotFoundException {
		return mesaService.mesasPorRestaurante(id_restaurante);
	}
}