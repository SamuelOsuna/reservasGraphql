package com.samuel.reservasServidorGraphql.service;

import com.samuel.reservasServidorGraphql.dao.RestauranteDao;
import com.samuel.reservasServidorGraphql.model.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteDao restauranteDao;

    @Transactional(readOnly = true)
    public List<Restaurante> getRestaurantes(){
        return restauranteDao.findAll();
    }

    @Transactional
    public Restaurante restaurantePorId(int id){
        return restauranteDao.findById(id).orElseThrow(null);
    }
}
