package com.economarket.api.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.economarket.api.entities.Preco;

@Repository
public interface PrecoRepository extends JpaRepository<Preco, Long> {

	@Query("SELECT p FROM Preco p WHERE p.produtoMarca.produto.id = :id")
	List<Preco> findByProdutoId(@Param("id") Long id);

}
