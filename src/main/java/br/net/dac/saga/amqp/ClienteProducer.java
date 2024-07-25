package br.net.dac.saga.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import br.net.dac.saga.dto.ClienteDTO;

public class ClienteProducer {
	@Autowired
	private RabbitTemplate template;
	
	@Autowired
	//private Queue queue;
	
	public void teste1(ClienteDTO clienteDto) {
		//Message msg = new Message(clienteDto.getNome().getBytes());
		template.convertAndSend("clientes.v1.cliente-novo", clienteDto);
	}
	public ClienteTransfer teste2(ClienteDTO clienteDto, String act) {
		ClienteTransfer clienteTransfer = new ClienteTransfer(clienteDto, "NULL", act);
		
		return (ClienteTransfer) this.template.convertSendAndReceive("clientes.v1.cliente-novo",clienteTransfer);
	}
}
