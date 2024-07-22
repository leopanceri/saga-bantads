package br.net.dac.saga.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.net.dac.saga.amqp.*;
import br.net.dac.saga.dto.*;

@CrossOrigin
@RestController
public class SagaRest {
	@Autowired
	private ClienteProducer clienteProducer;

	
	@PostMapping("/cadastro")
	public ResponseEntity<Object> saveCliente(@RequestBody ClienteDTO clienteDto){
		//ClienteTransfer clienteTransfer = clienteProducer.sendAndRecieve(clienteDto, "salva-cliente");
		clienteProducer.teste1(clienteDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Cliente, Conta e Usuário criados com sucesso!");
	}
}
