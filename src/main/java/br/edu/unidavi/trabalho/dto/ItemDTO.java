package br.edu.unidavi.trabalho.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class ItemDTO {
	
	Long produto;
	Integer quantidade;
	Double total;
}