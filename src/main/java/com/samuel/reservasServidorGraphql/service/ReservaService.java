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

@Service
public class ReservaService {

    @Autowired
    private ReservaDao reservaDao;
    private UsuarioDao usuarioDao;
    private MesaDao mesaDao;
    private RestauranteDao restauranteDao;

    @Transactional
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

    @Transactional
    public List<Reserva> reservasPorRestaurante(int id_restaurante) throws NotFoundException{
        Optional<Restaurante> optionalRestaurante = restauranteDao.findById(id_restaurante);
        if (optionalRestaurante.isPresent()){
            Restaurante restaurante = optionalRestaurante.get();
            List<Reserva> reservas = restaurante.getReservas();
            return reservas;
        } else {
            throw new NotFoundException("No se ha encontrado ningún restaurante con ese id");
        }
    }

    @Transactional
    public Reserva createReserva(int id_usuario, int id_mesa, int id_restaurante, String fecha, String tipo) throws NotFoundException {
        Reserva reserva = new Reserva();
        Optional<Usuario> optUsuario = usuarioDao.findById(id_usuario);
        Optional<Mesa> optMesa = mesaDao.findById(id_mesa);
        Optional<Restaurante> optRestaurante = restauranteDao.findById(id_restaurante);

        if (optUsuario.isPresent()){
            Usuario usuario = optUsuario.get();
            reserva.setUsuario(usuario);

            if (optMesa.isPresent()){
                Mesa mesa = optMesa.get();
                reserva.setMesa(mesa);

                if(optRestaurante.isPresent()){
                    Restaurante restaurante = optRestaurante.get();
                    reserva.setRestaurante(restaurante);
                    reserva.setFecha(fecha);
                    reserva.setTipo(tipo);

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
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public Reserva updateReserva(int id, String tipo) throws NotFoundException {
        Optional<Reserva> optReserva = reservaDao.findById(id);

        if (optReserva.isPresent()){
            Reserva reserva = optReserva.get();
            reserva.setTipo(tipo);
            reservaDao.save(reserva);
            return  reserva;
        } else {
            throw new NotFoundException("No hay ninguna reserva con ese id");
        }
    }
}
