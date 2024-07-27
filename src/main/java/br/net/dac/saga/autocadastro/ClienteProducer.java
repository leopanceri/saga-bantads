package br.net.dac.saga.autocadastro;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.net.dac.saga.dto.ClienteDTO;
import br.net.dac.saga.dto.ClienteTransfer;

public class ClienteProducer {
	
	@Autowired
	private RabbitTemplate template;
	/*
	public void teste1(ClienteDTO clienteDto) {
		//Message msg = new Message(clienteDto.getNome().getBytes());
		template.convertAndSend("clientes.v1.cliente-novo", clienteDto);
	}
	*/
	public void enviaCliente(ClienteDTO clienteDto, String msg) {
		ClienteTransfer clienteTransfer = new ClienteTransfer(clienteDto, msg);
		template.convertAndSend("clientes.v1.cliente-novo", clienteTransfer);
	}
}
