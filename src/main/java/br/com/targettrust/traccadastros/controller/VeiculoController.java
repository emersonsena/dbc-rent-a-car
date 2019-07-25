package br.com.targettrust.traccadastros.controller;



import br.com.targettrust.traccadastros.entidades.Veiculo;
import br.com.targettrust.traccadastros.repositorio.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;


    @PostMapping(value="/criarVeiculo",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<@Valid Veiculo> addVeiculo(@Valid @RequestBody Veiculo veiculo){
        if(veiculo == null || veiculo.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(veiculoRepository.save(veiculo));
    }



    @PutMapping(value="/updateVeiculo",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<Veiculo> updateVeiculo(@Valid @RequestBody Veiculo veiculo) {
        if(veiculo == null || veiculo.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(veiculoRepository.save(veiculo));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Veiculo> deleteByID(@PathVariable("id") Long id) {
        if(veiculoRepository.findById(id).isPresent()) {
            veiculoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
