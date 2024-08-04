package br.net.dac.saga.autocadastro;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.net.dac.saga.config.rabbitmq.RabbitMQConfig;
import br.net.dac.saga.dto.ClienteContaDTO;
import br.net.dac.saga.dto.ClienteDTO;
import br.net.dac.saga.dto.ClienteTransfer;
import br.net.dac.saga.dto.UsuarioDTO;
import br.net.dac.saga.services.EmailService;
import br.net.dac.saga.services.SenhaService;

@Component
public class OquestradorAutoCadastro {

	@Autowired
	private RabbitTemplate template;
	@Autowired
	private ClienteProducer clienteProducer;
	@Autowired
	private EmailService emailService;
	@Autowired
	private SenhaService senhaService;
	
	
	public static final String FILA_REGISTRO_USUARIO = "fila-test";
	
	@RabbitListener(queues= RabbitMQConfig.FILA_CLIENTE_RESPOSTA)
	public void recebeClienteCadastrado(ClienteTransfer clienteTransfer) {
		switch (clienteTransfer.getMessage()){
		case "FALHA":
		case "REMOVIDO":
			emailService.emailClienteFalhaCadastro(clienteTransfer.getClienteDto().getEmail());
			break;
		case "REJEITADO":
			emailService.emailClienteRejeitado(clienteTransfer.getClienteDto().getEmail(), clienteTransfer.getClienteDto().getMotivo());
			break;			
		default:
			ClienteContaDTO clienteConta = new ClienteContaDTO();
			clienteConta.setClienteId(clienteTransfer.getClienteDto().getId());
			clienteConta.setSalario(clienteTransfer.getClienteDto().getSalario());
			clienteConta.setEmail(clienteTransfer.getClienteDto().getEmail());
			clienteConta.setMensagem(clienteTransfer.getMessage());
			System.out.print(clienteTransfer.getClienteDto().getId());
			System.out.print(clienteTransfer.getClienteDto().getNome());
			template.convertAndSend(RabbitMQConfig.FILA_REGISTRO_CONTA_CLIENTE, clienteConta);
			break;
		}
	}

	@RabbitListener(queues = RabbitMQConfig.FILA_CONTA_RESPOSTA)
	public void respostaCadastroNovaConta(ClienteContaDTO clienteConta) {
		switch (clienteConta.getMensagem()) {
		case "FALHA":
			ClienteDTO ct = new ClienteDTO();
			ct.setId(clienteConta.getClienteId());
			ct.setEmail(clienteConta.getEmail());
			clienteProducer.enviaCliente(ct, "REMOVER");
			break;
		case "APROVADO":
			UsuarioDTO usuarioDto = new UsuarioDTO();
			usuarioDto.setClienteId(clienteConta.getClienteId().toString());
			usuarioDto.setContaId(clienteConta.getIdConta().toString());
			usuarioDto.setPerfil("CLIENTE");
			usuarioDto.setSenha(senhaService.passwordGen());
			usuarioDto.setUsuario(clienteConta.getEmail());
			emailService.emailClienteAprovado(usuarioDto.getUsuario(), usuarioDto.getSenha());
			template.convertAndSend(FILA_REGISTRO_USUARIO,  usuarioDto);
		}		
	}
}