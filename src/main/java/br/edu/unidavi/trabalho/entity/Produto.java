package br.edu.unidavi.trabalho.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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
public class Produto {

	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;

	String nome;
	String descricao;
	String marca;
	Double valor;
}
