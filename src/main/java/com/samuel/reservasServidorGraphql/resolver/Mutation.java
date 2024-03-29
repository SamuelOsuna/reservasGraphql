package com.samuel.reservasServidorGraphql.resolver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.samuel.reservasServidorGraphql.dao.MesaDao;
import com.samuel.reservasServidorGraphql.dao.ReservaDao;
import com.samuel.reservasServidorGraphql.dao.RestauranteDao;
import com.samuel.reservasServidorGraphql.dao.UsuarioDao;
import com.samuel.reservasServidorGraphql.errors.CustomError;
import com.samuel.reservasServidorGraphql.model.Mesa;
import com.samuel.reservasServidorGraphql.model.Reserva;
import com.samuel.reservasServidorGraphql.service.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.samuel.reservasServidorGraphql.model.Restaurante;
import com.samuel.reservasServidorGraphql.model.Usuario;

import javax.servlet.http.HttpServletRequest;

@Component
public class Mutation implements GraphQLMutationResolver {
    
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ReservaService reservaService;

	@Autowired
	private MesaService mesaService;

	@Autowired
	private KeyService keyService;

	@Autowired
	private RestauranteService restauranteService;

	@Autowired
	private HttpServletRequest request;

	//Método para obtener el Header que contiene el apikey
	private String getApiKey() {
		return request.getHeader("apikey");
	}

	//Métodos de Usuario
	public Usuario createUsuario(String nombre, String email, String contrasena, String imagen, String telefono) throws CustomError {
		if(keyService.compruebaKey(getApiKey())){
			return usuarioService.createUsuario(nombre, email, contrasena, imagen, telefono);
		} else {
			throw new CustomError("No tiene permisos para acceder a este método");
		}
	}

	public boolean deleteUsuario(int id) throws CustomError {
		if(keyService.compruebaKey(getApiKey())){
			return usuarioService.deleteUsuario(id);
		} else {
			throw new CustomError("No tiene permisos para acceder a este método");
		}
	}

	public Usuario updateUsuario(int id, String nombre, String email, String contrasena, String imagen, String telefono) throws CustomError, NotFoundException {
		if(keyService.compruebaKey(getApiKey())){
			return usuarioService.updateUsuario(id, nombre, email, contrasena, imagen, telefono);
		} else {
			throw new CustomError("No tiene permisos para acceder a este método");
		}
	}

	//Métodos de Reserva
	public Reserva createReserva(int id_usuario, int id_mesa, int id_restaurante, String fecha, String tipo, String notas) throws CustomError, NotFoundException {
		if(keyService.compruebaKey(getApiKey())){
			return reservaService.createReserva(id_usuario, id_mesa, id_restaurante, fecha, tipo, notas);
		} else {
			throw new CustomError("No tiene permisos para acceder a este método");
		}
	}

	public boolean deleteReserva(int id) throws CustomError {
		if(keyService.compruebaKey(getApiKey())){
			return reservaService.deleteReserva(id);
		} else {
			throw new CustomError("No tiene permisos para acceder a este método");
		}
	}

	public Reserva updateReserva(int id, String tipo, Boolean aceptada, String notas) throws CustomError, NotFoundException {
		if(keyService.compruebaKey(getApiKey())){
			return reservaService.updateReserva(id, tipo, aceptada, notas);
		} else {
			throw new CustomError("No tiene permisos para acceder a este método");
		}
	}

	//Métodos de Mesa
	public Mesa createMesa(int id_restaurante, int nmesa, int comensales, String imagen) throws Exception {
		if(keyService.compruebaKey(getApiKey())){
			return mesaService.createMesa(id_restaurante, nmesa, comensales, imagen);
		} else {
			throw new CustomError("No tiene permisos para acceder a este método");
		}
	}

	public boolean deleteMesa(int id) throws CustomError {
		if(keyService.compruebaKey(getApiKey())){
			return mesaService.deleteMesa(id);
		} else {
			throw new CustomError("No tiene permisos para acceder a este método");
		}
	}

	public Mesa updateMesa(int id, int nmesa, int comensales, String imagen) throws Exception {
		if(keyService.compruebaKey(getApiKey())){
			return mesaService.updateMesa(id, nmesa, comensales, imagen);
		} else {
			throw new CustomError("No tiene permisos para acceder a este método");
		}
	}

	//Métodos de Restaurante
	public boolean setFechasRestaurante(int id_restaurante, String fechas) throws Exception {
		if(keyService.compruebaKey(getApiKey())){
			return restauranteService.setFechasRestaurante(id_restaurante, fechas);
		} else {
			throw new CustomError("No tiene permisos para acceder a este método");
		}
	}
}