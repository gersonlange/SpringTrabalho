package br.edu.unidavi.trabalho.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unidavi.trabalho.dao.Pedido;
import br.edu.unidavi.trabalho.dao.PedidoRepository;
import br.edu.unidavi.trabalho.dao.PedidoResource;
import br.edu.unidavi.trabalho.dao.PedidoResourceAssembler;

@RestController
@RequestMapping("/pedidos")
public class PedidoRestController {

	@Autowired
	PedidoRepository repository;
	
	PedidoResourceAssembler assembler = new PedidoResourceAssembler();
	
	@PostConstruct
	public void init() {
//		repository.save(new Pedido(1l, "John", 11111, "john@john.com", new Date()));
//		repository.save(new Pedido(2l, "Steve", 22222, "steve.stevent@st.com", new Date()));
//		repository.save(new Pedido(3l, "Mary", 33333, "mary@robinson.com", new Date()));
//		repository.save(new Pedido(4l, "Kate", 44444,"kate@kate.com", new Date()));
//		repository.save(new Pedido(5l, "Diana", 55555,"diana@doll.com", new Date()));		
	}
	
	@Secured("ROLE_USER")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<PedidoResource>> getAll() {
		return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/{id}")
	public ResponseEntity<PedidoResource> get(@PathVariable Long id) {
		Pedido pedido = repository.findOne(id);
		if (pedido != null) {			
			return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PostMapping
	public ResponseEntity<PedidoResource> create(@RequestBody Pedido pedido) {
		pedido = repository.save(pedido);
		if (pedido != null) {
			return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PutMapping("/{id}")
	public ResponseEntity<PedidoResource> update(@PathVariable Long id, @RequestBody Pedido pedido) {
		if (pedido != null) {
			pedido.setId(id);
			pedido = repository.save(pedido);
			return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@DeleteMapping("/{id}")
	public ResponseEntity<PedidoResource> delete(@PathVariable Long id) {
		Pedido pedido = repository.findOne(id);
		if (pedido != null) {
			repository.delete(pedido);
			return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/numero/{numero}")
	public ResponseEntity<List<PedidoResource>> findByNome(@PathVariable Long numero) {
		return new ResponseEntity<>(assembler.toResources(repository.findByNumero(numero)), HttpStatus.OK);
	}
}
