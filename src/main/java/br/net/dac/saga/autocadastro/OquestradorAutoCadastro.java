package br.net.dac.saga.autocadastro;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.net.dac.saga.dto.*;

@Component
public class OquestradorAutoCadastro {
	
	@Autowired
	private RabbitTemplate template;
	
	@Autowired
	private ClienteProducer clienteProducer;
	
	
	
	@RabbitListener(queues= "FILA-CLIENTE-RESPOSTA")
	public void recebeClienteCadastrado(ClienteTransfer clienteTransfer) {
		if(clienteTransfer.getMessage().equals("CRIADO")) {
			System.out.print(clienteTransfer.getClienteDto().getId());
			System.out.print(clienteTransfer.getClienteDto().getNome());
			template.convertAndSend("FILA_REGISTRO_CONTA_CLIENTE", clienteTransfer);
		}
		if(clienteTransfer.getMessage().equals("ATUALIZADO")) {
			template.convertAndSend("FILA_REGISTRO_CONTA_CLIENTE", clienteTransfer);
		}
		if(clienteTransfer.getMessage().equals("FALHA")) {
			UsuarioDTO usuarioDto = new UsuarioDTO();
			usuarioDto.setUsuario(clienteTransfer.getClienteDto().getEmail());
			usuarioDto.setPerfil("CLIENTE");
			template.convertAndSend("FILA-FALHA-CADASTRO-CLIENTE", usuarioDto);
		}
	}
	
	@RabbitListener(queues = "FILA-CONTA-RESPOSTA")
	public void respostaCadastroNovaConta(ClienteTransfer clienteTransfer) {
		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto.setUsuario(clienteTransfer.getClienteDto().getEmail());
		usuarioDto.setPerfil("CLIENTE");
		switch (clienteTransfer.getMessage()) {
		case "SUCESSO":
			template.convertAndSend("fila-test", usuarioDto);
			break;
		case "FALHA":
			template.convertAndSend("FILA-FALHA-CADASTRO-CLIENTE", usuarioDto);
			clienteProducer.enviaCliente(clienteTransfer.getClienteDto(), "REMOVER");
		}
	}
}
