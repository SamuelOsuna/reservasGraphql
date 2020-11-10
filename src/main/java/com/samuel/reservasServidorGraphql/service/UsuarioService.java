package com.samuel.reservasServidorGraphql.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.samuel.reservasServidorGraphql.model.Restaurante;
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
	
	@Autowired
	private RestauranteDao restauranteDao;
	
	@Transactional(readOnly = true)
	public List<Restaurante> getSeries() {
		return restauranteDao.findAll();
	}
	
	@Transactional(readOnly = true)
	public Restaurante getSeries(Integer id) {
		Optional<Restaurante> series = restauranteDao.findById(id);
        return series.orElseThrow(() -> new IllegalArgumentException("Series 'id' not found!!!")  );
	}
	
	@Transactional
	public Usuario createCharacter(String name, String nickname, String occupation, LocalDate dateofbirth, Integer seriesId) {
		Usuario character = new Usuario(name, nickname, occupation, dateofbirth);
		Restaurante restaurante = getSeries(seriesId);
        character.setSeries(restaurante);
        return usuarioDao.save(character);
	}
	
	@Transactional(readOnly = true)
	public List<Usuario> getCharacters() {
		return usuarioDao.findAll();
	}
	
	@Transactional(readOnly = true)
	public Usuario getCharacter(Integer id) {
		Optional<Usuario> characterOpt = usuarioDao.findById(id);
		return characterOpt.orElseThrow(() -> new IllegalArgumentException("Character 'id' not found!!!"));
	}

}
