package com.samuel.reservasServidorGraphql.dao;

import com.samuel.reservasServidorGraphql.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyDao extends JpaRepository<Key, Integer> {
}
