package com.samuel.reservasServidorGraphql.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Transactional
	public List<Usuario> usuariosPorNombre(String nombre){
		List<Usuario> finalUsuarios = new ArrayList<>();
		List<Usuario> usuarios = usuarioDao.findAll();
		for (Usuario usuario:usuarios){
			if(usuario.getNombre().equalsIgnoreCase(nombre)){
				finalUsuarios.add(usuario);
			}
		}
		return finalUsuarios;
	}

	@Transactional
	public Usuario usuarioPorId(int id){
		return usuarioDao.findById(id).orElseThrow(null);
	}
	
	@Transactional
	public Usuario createUsuario(String nombre, String email, String contrasena, String imagen){
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setEmail(email);
		usuario.setContrasena(contrasena);

		if (imagen != null){
			usuario.setImagen(imagen);
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
	public Usuario updateUsuario(int id, String nombre, String email, String contrasena, String imagen) throws NotFoundException {
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
			usuarioDao.save(usuario);
			return usuario;
		}else{
			throw new NotFoundException("No se ha encontrado el usuario");
		}
	}
}
