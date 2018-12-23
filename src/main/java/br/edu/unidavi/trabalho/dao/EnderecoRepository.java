package br.edu.unidavi.trabalho.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	List<Endereco> findByCep(String cep);
	
	List<Endereco> findByRua(String rua);
	
	List<Endereco> findByCidade(String cidade);
	
	List<Endereco> findByEstado(String estado);
}
