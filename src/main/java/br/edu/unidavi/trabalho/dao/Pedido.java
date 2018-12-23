package br.edu.unidavi.trabalho.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Pedido {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@ManyToOne
    @JoinColumn(name="id_cliente")
	Cliente cliente;
	
	Long numero;
	Double total;
	Date dataCriacao;
}
