package br.edu.unidavi.trabalho.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unidavi.trabalho.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
