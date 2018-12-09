package br.edu.unidavi.trabalho.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unidavi.trabalho.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
