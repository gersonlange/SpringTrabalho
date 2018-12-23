package br.edu.unidavi.trabalho.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	Long id;

	String nome;
	String email;
	String cpf;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	Date dataNascimento;
	
	@ManyToOne
    @JoinColumn(name="id_endereco")
	Endereco endereco;
}
