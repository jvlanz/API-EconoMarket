package com.economarket.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.economarket.api.entities.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

}

