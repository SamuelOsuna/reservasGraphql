package com.samuel.reservasServidorGraphql.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.samuel.reservasServidorGraphql.errors.CustomError;
import com.samuel.reservasServidorGraphql.model.Restaurante;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samuel.reservasServidorGraphql.dao.UsuarioDao;
import com.samuel.reservasServidorGraphql.dao.RestauranteDao;
import com.samuel.reservasServidorGraphql.model.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	@Transactional(readOnly = true)
	public List<Usuario> usuariosPorNombre(String nombre){
		List<Usuario> finalUsuarios = new ArrayList<>();
		usuarioDao.findAll().forEach(usuario -> {
			if (usuario.getNombre().equalsIgnoreCase(nombre)){
				finalUsuarios.add(usuario);
			}
		});
		if (finalUsuarios != null && !finalUsuarios.isEmpty()){
			return finalUsuarios;
		} else {
			throw new CustomError("No se ha encontrado ningún usuario con el nombre: " + nombre);
		}

	}

	@Transactional(readOnly = true)
	public Usuario usuarioPorId(int id){
		return usuarioDao.findById(id).orElseThrow(null);
	}
	
	@Transactional
	public Usuario createUsuario(String nombre, String email, String contrasena, String imagen, String telefono){
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setEmail(email);
		usuario.setContrasena(contrasena);

		if (imagen != null){
			usuario.setImagen(imagen);
		}
		if (telefono != null){
			usuario.setTelefono(telefono);
		}

		usuarioDao.save(usuario);

		return usuario;
	}

	@Transactional
	public boolean deleteUsuario(int id){
		try {
			usuarioDao.deleteById(id);
			return true;
		}catch (Exception e){
			return false;
		}
	}

	@Transactional
	public Usuario updateUsuario(int id, String nombre, String email, String contrasena, String imagen, String telefono){
		Usuario usuario = new Usuario();
		Optional<Usuario> optUsuario = usuarioDao.findById(id);

		if (optUsuario.isPresent()){
			usuario = optUsuario.get();
			if (nombre != null){
				usuario.setNombre(nombre);
			}
			if (email != null){
				usuario.setEmail(email);
			}
			if (contrasena != null){
				usuario.setContrasena(contrasena);
			}
			if (imagen != null){
				usuario.setImagen(imagen);
			}
			if (telefono != null){
				usuario.setTelefono(telefono);
			}
			usuarioDao.save(usuario);
			return usuario;
		}else{
			throw new CustomError("No se ha encontrado el usuario");
		}
	}
}
