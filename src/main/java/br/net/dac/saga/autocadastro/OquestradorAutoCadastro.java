package br.net.dac.saga.autocadastro;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.net.dac.saga.dto.ClienteContaDTO;
import br.net.dac.saga.dto.ClienteDTO;
import br.net.dac.saga.dto.ClienteTransfer;
import br.net.dac.saga.dto.UsuarioDTO;

@Component
public class OquestradorAutoCadastro {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private ClienteProducer clienteProducer;
	
	@Autowired
    private ObjectMapper objectMapper;

	
	public static final String FILA_CLIENTE_RESPOSTA = "FILA_CLIENTE_RESPOSTA";
	public static final String FILA_ATUALIZA_STATUS ="FILA_ATUALIZA_STATUS";
	public static final String FILA_FALHA_CADASTRO_CLIENTE ="FILA_FALHA_CADASTRO_CLIENTE";

	@RabbitListener(queues= FILA_CLIENTE_RESPOSTA)
	public void recebeClienteCadastrado(ClienteTransfer clienteTransfer) {
		if(clienteTransfer.getMessage().equals("FALHA")) {
			UsuarioDTO usuarioDto = new UsuarioDTO();
			usuarioDto.setUsuario(clienteTransfer.getClienteDto().getEmail());
			usuarioDto.setPerfil("CLIENTE");
			template.convertAndSend(FILA_FALHA_CADASTRO_CLIENTE, usuarioDto);
		}else {
			ClienteContaDTO clienteConta = new ClienteContaDTO();
			clienteConta.setClienteId(clienteTransfer.getClienteDto().getId());
			clienteConta.setSalario(clienteTransfer.getClienteDto().getSalario());
			clienteConta.setEmail(clienteTransfer.getClienteDto().getEmail());
			clienteConta.setMensagem(clienteTransfer.getMessage());
			System.out.print(clienteTransfer.getClienteDto().getId());
			System.out.print(clienteTransfer.getClienteDto().getNome());
			
			template.convertAndSend("FILA_REGISTRO_CONTA_CLIENTE", clienteConta);
		}
		
	}

	@RabbitListener(queues = "FILA_CONTA_RESPOSTA")
	public void respostaCadastroNovaConta(ClienteContaDTO clienteConta) {
		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto.setUsuario(clienteConta.getEmail());
		usuarioDto.setSenha("bantads");
		usuarioDto.setPerfil("CLIENTE");
		usuarioDto.setClienteId(clienteConta.getClienteId().toString());
		usuarioDto.setContaId(clienteConta.getIdConta().toString());
		switch (clienteConta.getMensagem()) {
		case "SUCESSO":
			template.convertAndSend("fila-test", usuarioDto);
			break;
		case "FALHA":
			template.convertAndSend(FILA_FALHA_CADASTRO_CLIENTE, usuarioDto);
			ClienteDTO ct = new ClienteDTO();
			ct.setId(clienteConta.getClienteId());
			clienteProducer.enviaCliente(ct, "REMOVER");
			break;
		}
	}
}
