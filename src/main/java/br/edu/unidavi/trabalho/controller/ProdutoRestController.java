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

import br.edu.unidavi.trabalho.dao.Produto;
import br.edu.unidavi.trabalho.dao.ProdutoRepository;
import br.edu.unidavi.trabalho.dao.ProdutoResource;
import br.edu.unidavi.trabalho.dao.ProdutoResourceAssembler;

@RestController
@RequestMapping("/produtos")
public class ProdutoRestController {

	@Autowired
	ProdutoRepository produtoRepository;
	
	ProdutoResourceAssembler assembler = new ProdutoResourceAssembler();
	
	@PostConstruct
	public void init() {
		produtoRepository.save(new Produto(1l, "Tomate", "Tomate fresco", "Da lavoura", 2.25d));
		produtoRepository.save(new Produto(2l, "Feijao", "Feijao escolhido", "Joazinho", 6.75d));
//		produtoRepository.save(new Produto(3l, "Mary", 33333, "mary@robinson.com", new Date()));
//		produtoRepository.save(new Produto(4l, "Kate", 44444,"kate@kate.com", new Date()));
//		produtoRepository.save(new Produto(5l, "Diana", 55555,"diana@doll.com", new Date()));		
	}
	
	@Secured("ROLE_USER")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ProdutoResource>> getAll() {
		return new ResponseEntity<>(assembler.toResources(produtoRepository.findAll()), HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResource> get(@PathVariable Long id) {
		Produto produto = produtoRepository.findOne(id);
		if (produto != null) {			
			return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PostMapping
	public ResponseEntity<ProdutoResource> create(@RequestBody Produto produto) {
		produto = produtoRepository.save(produto);
		if (produto != null) {
			return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);					
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResource> update(@PathVariable Long id, @RequestBody Produto produto) {
		if (produto != null) {
			produto.setId(id);
			produto = produtoRepository.save(produto);
			return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@Secured("ROLE_MANAGER")
	@DeleteMapping("/{id}")
	public ResponseEntity<ProdutoResource> delete(@PathVariable Long id) {
		Produto produto = produtoRepository.findOne(id);
		if (produto != null) {
			produtoRepository.delete(produto);
			return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<ProdutoResource>> findByNome(@PathVariable String nome) {
		return new ResponseEntity<>(assembler.toResources(produtoRepository.findByNomeContainingIgnoreCase(nome)), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/marca/{marca}")
	public ResponseEntity<List<ProdutoResource>> findByMarca(@PathVariable String marca) {
		return new ResponseEntity<>(assembler.toResources(produtoRepository.findByMarcaContainingIgnoreCase(marca)), HttpStatus.OK);
	}
}
