package com.samuel.reservasServidorGraphql.service;

import com.samuel.reservasServidorGraphql.dao.MesaDao;
import com.samuel.reservasServidorGraphql.dao.ReservaDao;
import com.samuel.reservasServidorGraphql.dao.RestauranteDao;
import com.samuel.reservasServidorGraphql.dao.UsuarioDao;
import com.samuel.reservasServidorGraphql.model.Mesa;
import com.samuel.reservasServidorGraphql.model.Reserva;
import com.samuel.reservasServidorGraphql.model.Restaurante;
import com.samuel.reservasServidorGraphql.model.Usuario;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservaService {

    @Autowired
    private ReservaDao reservaDao;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private MesaDao mesaDao;

    @Autowired
    private RestauranteDao restauranteDao;

    @Transactional(readOnly = true)
    public List<Reserva> reservasPorUsuario(int id_usuario) throws NotFoundException{
        Optional<Usuario> optionalUsuario = usuarioDao.findById(id_usuario);
        if(optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();
            List<Reserva> reservas = usuario.getReservas();
            return reservas;
        } else {
            throw new NotFoundException("No se ha encontrado ningún usuario con ese id");
        }
    }

    @Transactional(readOnly = true)
    public Set<Reserva> reservasPorRestaurante(int id_restaurante) throws NotFoundException{
        Optional<Restaurante> optionalRestaurante = restauranteDao.findById(id_restaurante);
        if (optionalRestaurante.isPresent()){
            Restaurante restaurante = optionalRestaurante.get();
            Set<Reserva> reservas = restaurante.getReservas();
            return reservas;
        } else {
            throw new NotFoundException("No se ha encontrado ningún restaurante con ese id");
        }
    }

    @Transactional
    public Reserva createReserva(int id_usuario, int id_mesa, int id_restaurante, String fecha, String tipo) throws NotFoundException {
        Reserva reserva = new Reserva();

        if (usuarioDao.findById(id_usuario).isPresent()){
            Usuario usuario = usuarioDao.findById(id_usuario).get();
            reserva.setUsuario(usuario);

            if (mesaDao.findById(id_mesa).isPresent()){
                Mesa mesa = mesaDao.findById(id_mesa).get();
                reserva.setMesa(mesa);

                if( restauranteDao.findById(id_restaurante).isPresent()){
                    Restaurante restaurante =  restauranteDao.findById(id_restaurante).get();
                    reserva.setRestaurante(restaurante);
                    reserva.setFecha(fecha);
                    reserva.setTipo(tipo);
                    reserva.setNombre(restaurante.getNombre());

                    reservaDao.save(reserva);
                    return reserva;

                } else {
                    throw new NotFoundException("No existe nigun restaurante con ese id");
                }

            } else{
                throw new NotFoundException("No existe niguna mesa con ese id");
            }

        } else {
            throw new NotFoundException("No existe nigún usuario con ese id");
        }
    }

    @Transactional
    public boolean deleteReserva(int id){
        try {
            reservaDao.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Transactional
    public Reserva updateReserva(int id, String tipo, Boolean aceptada) throws NotFoundException {
        Optional<Reserva> optReserva = reservaDao.findById(id);

        if (optReserva.isPresent()){
            Reserva reserva = optReserva.get();
            if(tipo != null){
                reserva.setTipo(tipo);
            }
            if(aceptada != null){
                reserva.setAceptada(aceptada);
            }
            reservaDao.save(reserva);
            return  reserva;
        } else {
            throw new NotFoundException("No hay ninguna reserva con ese id");
        }
    }
}
