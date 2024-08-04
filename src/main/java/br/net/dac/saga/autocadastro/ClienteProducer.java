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

	public static final String FILA_CLIENTE_CRUD = "FILA_CLIENTE_CRUD";

	public ResponseEntity<Object> enviaCliente(ClienteDTO clienteDto, String msg) {
		ClienteTransfer clienteTransfer = new ClienteTransfer(clienteDto, msg);
		template.convertAndSend(FILA_CLIENTE_CRUD, clienteTransfer);
		return ResponseEntity.status(HttpStatus.CREATED).body("SOLICITAÇÃO DE CADASTRO REALIZADA COM SUCESSO");
	}
}
