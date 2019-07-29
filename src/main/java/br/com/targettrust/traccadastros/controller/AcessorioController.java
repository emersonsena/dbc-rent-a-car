package br.com.targettrust.traccadastros.controller;


import java.util.List;
import java.util.Optional;

import br.com.targettrust.traccadastros.entidades.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.targettrust.traccadastros.entidades.Acessorio;
import br.com.targettrust.traccadastros.repositorio.AcessorioRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("acessorios")
public class AcessorioController {

	@Autowired
	private AcessorioRepository acessorioRepository;


	@PostMapping(value="/criarAcessorio", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Acessorio> addAcessorio(@Valid @RequestBody Acessorio acessorio) {

		if(acessorio == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(acessorioRepository.save(acessorio));
	}

	@PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Acessorio> updateAcessorio(@PathVariable("id") Long id,
													 @Valid @RequestBody Acessorio acessorio) {
		Optional<Acessorio> dbAcessorio = acessorioRepository.findById(id);
		if(dbAcessorio.isPresent()) {
			dbAcessorio.get().setDescricao(acessorio.getDescricao());
			dbAcessorio.get().setModelos(acessorio.getModelos());
			dbAcessorio.get().setVeiculos(acessorio.getVeiculos());
			dbAcessorio.get().setVersion(acessorio.getVersion());
			acessorioRepository.save(dbAcessorio.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public HttpEntity<List<Acessorio>> listAll(){
		return ResponseEntity.ok(acessorioRepository.findAll());
	}

	@GetMapping("/search")
	public HttpEntity<List<Acessorio>> search(
			@RequestParam(name="id", required = false) Long id,
			@RequestParam(name="descricao", required = false) String descricao) {
		List<Acessorio> acessorios =
				acessorioRepository.search(id, descricao);
		return acessorios == null || acessorios.isEmpty() ?
				ResponseEntity.noContent().build() :
					ResponseEntity.ok(acessorios);
	}

	@GetMapping("/descricao/{descricao}")
	public HttpEntity<Acessorio> findByDescricao(@PathVariable("descricao") String descricao){
		Optional<Acessorio> acessorio = acessorioRepository.findByDescricao(descricao.toUpperCase());
		if(acessorio.isPresent()) {
			return ResponseEntity.ok(acessorio.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/id/{id}")
	public HttpEntity<Acessorio> findById(@PathVariable("id") Long id){
		Optional<Acessorio> acessorio = acessorioRepository.findById(id);
		if(acessorio.isPresent()) {
			return ResponseEntity.ok(acessorio.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}
}

