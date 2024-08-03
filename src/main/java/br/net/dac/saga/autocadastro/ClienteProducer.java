package br.net.dac.saga.autocadastro;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.net.dac.saga.config.rabbitmq.RabbitMQConfig;
import br.net.dac.saga.dto.ClienteDTO;
import br.net.dac.saga.dto.ClienteTransfer;
import br.net.dac.saga.dto.StatusConta;

public class ClienteProducer {

	@Autowired
	private RabbitTemplate template;

	public ResponseEntity<Object> enviaCliente(ClienteDTO clienteDto, String msg) {
		ClienteTransfer clienteTransfer = new ClienteTransfer(clienteDto, msg);
		template.convertAndSend(RabbitMQConfig.FILA_CLIENTE_CRUD, clienteTransfer);
		return ResponseEntity.status(HttpStatus.CREATED).body("SOLICITAÇÃO DE CADASTRO REALIZADA COM SUCESSO");
	}
	
	public ResponseEntity<Object> aprovarCliente(long id) {
		LocalDateTime localDateTime = LocalDateTime.now();
		ClienteDTO clienteDto = new ClienteDTO(id, StatusConta.APROVADO, localDateTime, null);
		template.convertAndSend(RabbitMQConfig.FILA_ATUALIZA_STATUS, clienteDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("CLIENTE APROVADO");
	}
	
	public ResponseEntity<Object> reprovarCliente(long id, String motivo) {
		LocalDateTime localDateTime = LocalDateTime.now();
		ClienteDTO clienteDto = new ClienteDTO(id, StatusConta.REJEITADO, localDateTime, motivo);
		template.convertAndSend(RabbitMQConfig.FILA_ATUALIZA_STATUS, clienteDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("CLIENTE REPROVADO");
	}
}
