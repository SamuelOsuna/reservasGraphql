package com.samuel.reservasServidorGraphql.resolver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.samuel.reservasServidorGraphql.dao.MesaDao;
import com.samuel.reservasServidorGraphql.dao.ReservaDao;
import com.samuel.reservasServidorGraphql.dao.RestauranteDao;
import com.samuel.reservasServidorGraphql.dao.UsuarioDao;
import com.samuel.reservasServidorGraphql.model.Mesa;
import com.samuel.reservasServidorGraphql.model.Reserva;
import com.samuel.reservasServidorGraphql.service.MesaService;
import com.samuel.reservasServidorGraphql.service.ReservaService;
import com.samuel.reservasServidorGraphql.service.RestauranteService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.samuel.reservasServidorGraphql.model.Restaurante;
import com.samuel.reservasServidorGraphql.model.Usuario;
import com.samuel.reservasServidorGraphql.service.UsuarioService;
import sun.usagetracker.UsageTrackerClient;

@Component
public class Mutation implements GraphQLMutationResolver {
    
	@Autowired
	private UsuarioService usuarioService;
	private ReservaService reservaService;
	private MesaService mesaService;

	//Métodos de Usuario
	public Usuario createUsuario(String nombre, String email, String contrasena, String imagen){
		return usuarioService.createUsuario(nombre, email, contrasena, imagen);
	}

	public boolean deleteUsuario(int id){
		return usuarioService.deleteUsuario(id);
	}

	public Usuario updateUsuario(int id, String nombre, String email, String contrasena, String imagen) throws NotFoundException {
		return usuarioService.updateUsuario(id, nombre, email, contrasena, imagen);
	}

	//Métodos de Reserva
	public Reserva createReserva(int id_usuario, int id_mesa, int id_restaurante, String fecha, String tipo) throws NotFoundException {
		return reservaService.createReserva(id_usuario, id_mesa, id_restaurante, fecha, tipo);
	}

	public boolean deleteReserva(int id){
		return reservaService.deleteReserva(id);
	}

	public Reserva updateReserva(int id, String tipo) throws NotFoundException {
		return reservaService.updateReserva(id, tipo);
	}

	//Métodos de Mesa
	public Mesa createMesa(int id_restaurante, int nmesa, int comensales, String imagen) throws NotFoundException {
		return mesaService.createMesa(id_restaurante, nmesa, comensales, imagen);
	}

	public boolean deleteMesa(int id){
		return mesaService.deleteMesa(id);
	}

	public Mesa updateMesa(int id, int nmesa, int comensales, String imagen) throws NotFoundException {
		return mesaService.updateMesa(id, nmesa, comensales, imagen);
	}
}