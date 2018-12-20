package br.edu.unidavi.trabalho.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	List<Produto> findByNome(String nome);
}
