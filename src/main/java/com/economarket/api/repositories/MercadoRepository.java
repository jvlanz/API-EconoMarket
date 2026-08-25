package com.economarket.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.economarket.api.entities.Mercado;

@Repository
public interface MercadoRepository extends JpaRepository<Mercado, Long> {

}
