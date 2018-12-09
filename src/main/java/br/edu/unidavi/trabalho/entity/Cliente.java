package br.edu.unidavi.trabalho.entity;

import java.util.Date;

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
public class Cliente {

	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;

	String nome;
	String email;
	String cpf;
	Date dataNascimento;
	Endereco endereco;
}
