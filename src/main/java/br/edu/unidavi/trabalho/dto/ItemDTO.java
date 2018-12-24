package br.edu.unidavi.trabalho.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@Builder
@ToString
public class ItemDTO {
	
	Long produto;
	Integer quantidade;
	Double total;
}