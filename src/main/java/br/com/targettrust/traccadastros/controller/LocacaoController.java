package br.com.targettrust.traccadastros.controller;


import javax.validation.Valid;
import java.util.Optional;

import br.com.targettrust.traccadastros.entidades.Locacao;
import br.com.targettrust.traccadastros.repositorio.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("locacoes")
public class LocacaoController {

    @Autowired
    private LocacaoRepository locacaoRepository;


    // TODO 1 Implementar métodos para criação, alteração e cancelamento de reserva

    @PostMapping(value="/criarLocacao", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<Locacao> criarLocacao(@Valid @RequestBody Locacao locacao) {

        if(locacao == null) {
           return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(locacaoRepository.save(locacao));
    }

    @PutMapping(value="/updateLocacao", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<Locacao> updateReserva(@Valid @RequestBody Locacao locacao) {
        Optional<Locacao> dbLocacao = locacaoRepository.findById(locacao.getId());
        if(dbLocacao.isPresent()) {
            dbLocacao.get().setVeiculo(locacao.getVeiculo());
            dbLocacao.get().setDataInicial(locacao.getDataInicial());
            dbLocacao.get().setDataFinal(locacao.getDataFinal());
            dbLocacao.get().setValor(locacao.getValor());

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        locacaoRepository.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        locacaoRepository.deleteAll();
    }


}
