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

import br.edu.unidavi.trabalho.dao.Cliente;
import br.edu.unidavi.trabalho.dao.ClienteRepository;
import br.edu.unidavi.trabalho.dao.ClienteResource;
import br.edu.unidavi.trabalho.dao.ClienteResourceAssembler;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

	@Autowired
	ClienteRepository repository;
	
	ClienteResourceAssembler assembler = new ClienteResourceAssembler();
	
	@PostConstruct
	public void init() {
//		repository.save(new Cliente(1l, "John", 11111, "john@john.com", new Date()));
//		repository.save(new Cliente(2l, "Steve", 22222, "steve.stevent@st.com", new Date()));
//		repository.save(new Cliente(3l, "Mary", 33333, "mary@robinson.com", new Date()));
//		repository.save(new Cliente(4l, "Kate", 44444,"kate@kate.com", new Date()));
//		repository.save(new Cliente(5l, "Diana", 55555,"diana@doll.com", new Date()));		
	}
	
	@Secured("ROLE_USER")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ClienteResource>> getAll() {
		return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/{id}")
	public ResponseEntity<ClienteResource> get(@PathVariable Long id) {
		Cliente cliente = repository.findOne(id);
		if (cliente != null) {			
			return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PostMapping
	public ResponseEntity<ClienteResource> create(@RequestBody Cliente cliente) {
		cliente = repository.save(cliente);
		if (cliente != null) {
			return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PutMapping("/{id}")
	public ResponseEntity<ClienteResource> update(@PathVariable Long id, @RequestBody Cliente cliente) {
		if (cliente != null) {
			cliente.setId(id);
			cliente = repository.save(cliente);
			return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@DeleteMapping("/{id}")
	public ResponseEntity<ClienteResource> delete(@PathVariable Long id) {
		Cliente cliente = repository.findOne(id);
		if (cliente != null) {
			repository.delete(cliente);
			return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<ClienteResource>> findByNome(@PathVariable String nome) {
		return new ResponseEntity<>(assembler.toResources(repository.findByNome(nome)), HttpStatus.OK);
	}
}
