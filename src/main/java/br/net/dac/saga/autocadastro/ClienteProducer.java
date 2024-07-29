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
	
	public void enviaCliente(ClienteDTO clienteDto, String msg) {
		ClienteTransfer clienteTransfer = new ClienteTransfer(clienteDto, msg);
		template.convertAndSend("cliente-crud", clienteTransfer);
	}
}
