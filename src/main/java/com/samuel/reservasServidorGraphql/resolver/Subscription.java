package com.samuel.reservasServidorGraphql.resolver;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.samuel.reservasServidorGraphql.model.Reserva;
import com.samuel.reservasServidorGraphql.service.KeyService;
import com.samuel.reservasServidorGraphql.service.ReservaService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import javax.servlet.http.HttpServletRequest;

@Component
public class Subscription implements GraphQLSubscriptionResolver {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private KeyService keyService;

    @Autowired
    private HttpServletRequest request;

    //Método para obtener el Header que contiene el apikey
    private String getApiKey() {
        return request.getHeader("apikey");
    }

    public Publisher<Reserva> onReservaAdd(int id_restaurante){
        return reservaService.onReservaAdd(id_restaurante);
    }

    public Publisher<Reserva> onReservaUpdate(int id_usuario){
        return reservaService.onReservaUpdate(id_usuario);
    }

}
