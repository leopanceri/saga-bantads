package br.net.dac.saga.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.net.dac.saga.autocadastro.*;
import br.net.dac.saga.config.rabbitmq.*;
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
	public ResponseEntity<Object> saveCliente(@RequestBody ClienteDTO clienteDto){
		return clienteProducer.enviaCliente(clienteDto, "CRIAR");		
	}
	
	@PutMapping("/cliente/update")
	public ResponseEntity<Object> alteraCadastro(@RequestBody ClienteDTO clienteDto) {
		return clienteProducer.enviaCliente(clienteDto, "ATUALIZAR");
	}
}
