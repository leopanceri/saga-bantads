package br.net.dac.saga.autocadastro;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrchestradorAutoCadastro {
	
	@Autowired
	private RabbitTemplate template;
	
	
	@RabbitListener(queues= "cliente-resposta")
	public void recebeClienteCadastrado(ClienteTransfer clienteTransfer) {
		if(clienteTransfer.getMessage().equals("CRIADO")) {
			System.out.print(clienteTransfer.getClienteDto().getId());
			System.out.print(clienteTransfer.getClienteDto().getNome());
		}
	}
}
