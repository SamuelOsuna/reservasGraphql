package com.samuel.reservasServidorGraphql.service;

import com.samuel.reservasServidorGraphql.dao.MesaDao;
import com.samuel.reservasServidorGraphql.dao.RestauranteDao;
import com.samuel.reservasServidorGraphql.errors.CustomError;
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
    public List<Mesa> mesasPorRestaurante(int id_restaurante){
        Optional<Restaurante> optionalRestaurante = restauranteDao.findById(id_restaurante);
        if (optionalRestaurante.isPresent()){
            Restaurante restaurante = optionalRestaurante.get();
            return restaurante.getMesas();
        } else {
            throw new CustomError("No se ha encontrado ningun restaurante con ese id");
        }
    }

    @Transactional
    public Mesa createMesa(int id_restaurante, int nmesa, int comensales, String imagen){
        Mesa mesa = new Mesa();
        Optional<Restaurante> optRestaurante = restauranteDao.findById(id_restaurante);
        if (optRestaurante.isPresent()){
            Restaurante restaurante = optRestaurante.get();
            mesa.setRestaurante(restaurante);
            for(Mesa mesaobt:restaurante.getMesas()){
                if(mesaobt.getNmesa() != mesa.getNmesa()){
                    mesa.setNmesa(nmesa);
                }else{
                    throw new CustomError("Ya existe una mesa con este número");
                }
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
            throw new CustomError("No existe ningún restaurante con ese id");
        }
    }

    @Transactional
    public boolean deleteMesa(int id){
        try	{
            if(this.mesaDao.findById(id).isPresent()){
                Mesa mesa = this.mesaDao.findById(id).get();
                mesa.setRestaurante(null);
                mesa.setImagen("");
                mesaDao.save(mesa);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error en deletemesa: "+e);
            return false;
        }
    }

    @Transactional
    public Mesa updateMesa(int id, int nmesa, int comensales, String imagen) {
        Optional<Mesa> optMesa = this.mesaDao.findById(id);
        boolean pasa = true;
        if (optMesa.isPresent()){
            Mesa mesa = optMesa.get();

            for(Mesa mesaobt:mesa.getRestaurante().getMesas()){
                if(mesaobt.getNmesa() == nmesa){
                    pasa = false;
                }else{
                    throw new CustomError("Ya existe una mesa con este número");
                }
            }
            if (nmesa > 0 && pasa){
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
            throw new CustomError("No se ha encontrado ninguna mesa con ese id");
        }
    }
}
