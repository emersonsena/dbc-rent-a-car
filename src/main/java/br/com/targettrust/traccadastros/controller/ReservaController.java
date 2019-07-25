package br.com.targettrust.traccadastros.controller;

import br.com.targettrust.traccadastros.entidades.Reserva;
import br.com.targettrust.traccadastros.repositorio.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;


    // TODO 1 Implementar métodos para criação, alteração e cancelamento de reserva

    @PostMapping(value="/criarReserva", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<Reserva> addReserva(@Valid @RequestBody Reserva reserva) {

        if(reserva == null) {
           return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(reservaRepository.save(reserva));
    }

    @PutMapping(value="/updateReserva", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<Reserva> updateReserva(@Valid @RequestBody Reserva reserva) {
        Optional<Reserva> dbReserva = reservaRepository.findById(reserva.getId());
        if(dbReserva.isPresent()) {
            dbReserva.get().setVeiculo(reserva.getVeiculo());
            dbReserva.get().setDataCancelamento(reserva.getDataCancelamento());
            dbReserva.get().setDataInicial(reserva.getDataInicial());
            dbReserva.get().setDataFinal(reserva.getDataFinal());
            dbReserva.get().setEquipamentos(reserva.getEquipamentos());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        reservaRepository.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        reservaRepository.deleteAll();
    }


}
