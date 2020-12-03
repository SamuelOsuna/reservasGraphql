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

    @Transactional(readOnly = true)
    public Restaurante restaurantePorId(int id){
        return restauranteDao.findById(id).orElseThrow(null);
    }

    @Transactional
    public boolean setFechasRestaurante(int id_restaurante, String fechas) throws Exception {
        boolean guardado = false;

        Restaurante restaurante = restauranteDao.findById(id_restaurante).orElseThrow(null);
        if(restaurante != null){
            try{
                restaurante.setDiascerrado(fechas);
                guardado = true;
            }catch (Exception e){
                throw new Exception(e);
            }

        }

        return  guardado;
    }
}
