package com.samuel.reservasServidorGraphql.service;

import com.samuel.reservasServidorGraphql.dao.MesaDao;
import com.samuel.reservasServidorGraphql.dao.RestauranteDao;
import com.samuel.reservasServidorGraphql.model.Mesa;
import com.samuel.reservasServidorGraphql.model.Restaurante;
import javassist.NotFoundException;
import org.checkerframework.checker.units.qual.Substance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    @Autowired
    private MesaDao mesaDao;

    @Autowired
    private RestauranteDao restauranteDao;

    @Transactional(readOnly = true)
    public List<Mesa> mesasPorRestaurante(int id_restaurante) throws NotFoundException {
        Optional<Restaurante> optionalRestaurante = restauranteDao.findById(id_restaurante);
        if (optionalRestaurante.isPresent()){
            Restaurante restaurante = optionalRestaurante.get();
            return restaurante.getMesas();
        } else {
            throw new NotFoundException("No se ha encontrado ningun restaurante con ese id");
        }
    }

    @Transactional
    public Mesa createMesa(int id_restaurante, int nmesa, int comensales, String imagen) throws NotFoundException {
        Mesa mesa = new Mesa();
        Optional<Restaurante> optRestaurante = restauranteDao.findById(id_restaurante);
        if (optRestaurante.isPresent()){
            Restaurante restaurante =optRestaurante.get();
            mesa.setRestaurante(restaurante);
            mesa.setNmesa(nmesa);

            if (comensales > 0){
                mesa.setComensales(comensales);
            }
            if (imagen != null){
                mesa.setImagen(imagen);
            }

            this.mesaDao.save(mesa);
            return mesa;
        } else {
            throw new NotFoundException("No existe ningún restaurante con ese id");
        }
    }

    @Transactional
    public boolean deleteMesa(int id){
        try	{
            this.mesaDao.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public Mesa updateMesa(int id, int nmesa, int comensales, String imagen) throws NotFoundException {
        Optional<Mesa> optMesa = this.mesaDao.findById(id);
        if (optMesa.isPresent()){
            Mesa mesa = optMesa.get();

            if (nmesa > 0){
                mesa.setNmesa(nmesa);
            }
            if (comensales > 0){
                mesa.setComensales(comensales);
            }
            if (imagen != null){
                mesa.setImagen(imagen);
            }

            this.mesaDao.save(mesa);
            return mesa;

        } else {
            throw new NotFoundException("No se ha encontrado ninguna mesa con ese id");
        }
    }
}
