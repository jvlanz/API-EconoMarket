package com.economarket.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.economarket.api.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	boolean existsByEmail(String email); //serve para verificar se possui um email repetido

}
