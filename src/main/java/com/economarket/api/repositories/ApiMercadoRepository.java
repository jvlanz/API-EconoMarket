package com.economarket.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.economarket.api.entities.ApiMercado;

@Repository
public interface ApiMercadoRepository extends JpaRepository<ApiMercado, Long> {

}
