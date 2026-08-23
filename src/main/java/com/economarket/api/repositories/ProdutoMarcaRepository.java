package com.economarket.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.economarket.api.entities.ProdutoMarca;

@Repository
public interface ProdutoMarcaRepository extends JpaRepository<ProdutoMarca, Long> {

}

