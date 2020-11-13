package com.samuel.reservasServidorGraphql.resolver;

import java.util.List;
import java.util.Set;

import com.samuel.reservasServidorGraphql.model.Mesa;
import com.samuel.reservasServidorGraphql.model.Reserva;
import com.samuel.reservasServidorGraphql.model.Restaurante;
import com.samuel.reservasServidorGraphql.service.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.samuel.reservasServidorGraphql.model.Usuario;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

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

	@Autowired
	private KeyService keyService;

	@Autowired
	private HttpServletRequest request;

	//Método para obtener el Header que contiene el apikey
	private String getApiKey() {
		return request.getHeader("apikey");
	}

	//Métodos de Restaurante
	public List<Restaurante> restaurantes() throws Exception {
		if(keyService.compruebaKey(getApiKey())){
			return restauranteService.getRestaurantes();
		} else {
			throw new Exception("No tiene permisos para acceder a este método");
		}
	}

	public Restaurante restaurante(int id) throws Exception {
		if(keyService.compruebaKey(getApiKey())){
			return restauranteService.restaurantePorId(id);
		} else {
			throw new Exception("No tiene permisos para acceder a este método");
		}
	}

	//Métodos de Usuario
	public List<Usuario> usuariosPorNombre(String nombre) throws Exception {
		if(keyService.compruebaKey(getApiKey())){
			return usuarioService.usuariosPorNombre(nombre);
		} else {
			throw new Exception("No tiene permisos para acceder a este método");
		}
	}

	public Usuario usuario(int id) throws Exception {
		if(keyService.compruebaKey(getApiKey())){
			return usuarioService.usuarioPorId(id);
		} else {
			throw new Exception("No tiene permisos para acceder a este método");
		}
	}

	//Métodos de Reserva
	public List<Reserva> reservasPorUsuario(int id_usuario) throws Exception {
		if(keyService.compruebaKey(getApiKey())){
			return reservaService.reservasPorUsuario(id_usuario);
		} else {
			throw new Exception("No tiene permisos para acceder a este método");
		}
	}

	public List<Reserva> reservasPorRestaurante(int id_restaurante) throws Exception {
		if(keyService.compruebaKey(getApiKey())){
			return reservaService.reservasPorRestaurante(id_restaurante);
		} else {
			throw new Exception("No tiene permisos para acceder a este método");
		}
	}

	//Métodos de Mesa
	public List<Mesa> mesasPorRestaurante(int id_restaurante) throws Exception {
		if(keyService.compruebaKey(getApiKey())){
			return mesaService.mesasPorRestaurante(id_restaurante);
		} else {
			throw new Exception("No tiene permisos para acceder a este método");
		}
	}
}