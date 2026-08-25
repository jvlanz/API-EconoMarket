package com.economarket.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.economarket.api.entities.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

}
