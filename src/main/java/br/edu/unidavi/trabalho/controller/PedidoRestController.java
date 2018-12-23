package br.edu.unidavi.trabalho.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unidavi.trabalho.dao.Cliente;
import br.edu.unidavi.trabalho.dao.ClienteRepository;
import br.edu.unidavi.trabalho.dao.Item;
import br.edu.unidavi.trabalho.dao.ItemRepository;
import br.edu.unidavi.trabalho.dao.Pedido;
import br.edu.unidavi.trabalho.dao.PedidoRepository;
import br.edu.unidavi.trabalho.dao.Produto;
import br.edu.unidavi.trabalho.dao.ProdutoRepository;
import br.edu.unidavi.trabalho.dto.ItemDTO;
import br.edu.unidavi.trabalho.dto.PedidoDTO;
import br.edu.unidavi.trabalho.dto.PedidoRetDTO;

@RestController
@RequestMapping("/pedidos")
public class PedidoRestController {

	@Autowired
	PedidoRepository repository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ItemRepository itemRepository;

	@Secured("ROLE_MANAGER")
	@PostMapping
	public ResponseEntity<PedidoRetDTO> create(@RequestBody PedidoDTO dto) {
		
		Cliente cliente = clienteRepository.findOne(dto.getCliente());
		
		if ( cliente == null )
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		
		Pedido pedido = new Pedido(null, cliente, dto.getNumero(), dto.getTotal(), new Date());
		pedido = repository.save(pedido);

		if (pedido == null)
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		
		for (ItemDTO i : dto.getItems())
		{
			Produto produto = produtoRepository.findOne(i.getProduto());

			Item item = new Item(null, produto, i.getQuantidade(), i.getTotal(), pedido);

			item = itemRepository.save(item);
		}
		
		PedidoRetDTO ret = new PedidoRetDTO();
		ret.setId(pedido.getId());

		return new ResponseEntity<>(ret, HttpStatus.OK);					
	}
}
