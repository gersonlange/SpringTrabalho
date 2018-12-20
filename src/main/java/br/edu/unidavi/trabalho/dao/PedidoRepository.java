package br.edu.unidavi.trabalho.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findByNumero(Long numero);
}
