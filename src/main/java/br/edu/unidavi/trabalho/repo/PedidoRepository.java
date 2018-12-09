package br.edu.unidavi.trabalho.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unidavi.trabalho.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
