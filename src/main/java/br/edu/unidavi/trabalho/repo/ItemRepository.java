package br.edu.unidavi.trabalho.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unidavi.trabalho.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
