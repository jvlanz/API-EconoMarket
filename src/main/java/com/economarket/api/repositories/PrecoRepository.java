package com.economarket.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.economarket.api.entities.Preco;

@Repository
public interface PrecoRepository extends JpaRepository<Preco, Long> {

}
