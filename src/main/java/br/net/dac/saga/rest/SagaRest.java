package br.net.dac.saga.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import br.net.dac.saga.amqp.*;
import br.net.dac.saga.autocadastro.*;
import br.net.dac.saga.dto.*;

@CrossOrigin
@RestController
public class SagaRest {
	
	@Autowired
	private ClienteProducer clienteProducer;

	/*
	@PostMapping("/cadastro")
	public ResponseEntity<Object> saveCliente(@RequestBody ClienteDTO clienteDto){
		return clienteProducer.teste2(clienteDto, "CREATE");		
	}
	*/
	@PostMapping("/cadastro")
	public void saveCliente(@RequestBody ClienteDTO clienteDto){
		clienteProducer.enviaCliente(clienteDto, "CRIAR");		
	}
	
	@PutMapping("/cliente/update")
	public void alteraCadastro(@RequestBody ClienteDTO clienteDto) {
		clienteProducer.enviaCliente(clienteDto, "ATUALIZAR");
	}
}
