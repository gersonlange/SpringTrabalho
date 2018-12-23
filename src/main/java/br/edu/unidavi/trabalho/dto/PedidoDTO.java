package br.edu.unidavi.trabalho.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class PedidoDTO {

	Long cliente;
	Long numero;
	Double total;
	
	List<ItemDTO> items;
}
