package br.edu.unidavi.trabalho.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@Builder
@ToString
public class PedidoDTO {

	Long cliente;
	Long numero;
	Double total;
	
	List<ItemDTO> items;
}
