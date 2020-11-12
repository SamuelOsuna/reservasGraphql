package com.samuel.reservasServidorGraphql.service;

import com.samuel.reservasServidorGraphql.dao.KeyDao;
import com.samuel.reservasServidorGraphql.model.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class KeyService {

    @Autowired
    private KeyDao keyDao;

    @Transactional(readOnly = true)
    public boolean compruebaKey(String key){
        boolean respuesta = false;
        List<Key> keys = keyDao.findAll();
        for(Key obkey:keys){
            if(obkey.getCodigo().equals(key) && obkey.isActivo() == true){
                respuesta = true;

            }
        }
        return respuesta;
    }
}
