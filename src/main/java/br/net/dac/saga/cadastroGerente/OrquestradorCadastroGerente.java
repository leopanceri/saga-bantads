package br.net.dac.saga.cadastroGerente;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.net.dac.saga.config.rabbitmq.RabbitMQConfig;
import br.net.dac.saga.dto.GerenteDTO;
import br.net.dac.saga.dto.UsuarioDTO;
import br.net.dac.saga.services.EmailService;
import br.net.dac.saga.services.SenhaService;

@Component
public class OrquestradorCadastroGerente {

	public static final String FILA_REGISTRO_USUARIO = "fila-test";
	
	@Autowired
	private RabbitTemplate template;

	@Autowired
	private SenhaService senhaService;
	
	@Autowired
	EmailService emailService;

	@RabbitListener(queues= "FILA_GERENTE_CADASTRADO")
	public void recebeGerenteCadastrado(GerenteDTO gerenteDto) {
		try {
			System.out.print("gId: " + gerenteDto.getId() + "Email: " +gerenteDto.getEmail());
			template.convertAndSend("FILA_ATRIBUI_CONTA_GERENTE", gerenteDto.getId().toString());
			UsuarioDTO usuarioDto = new UsuarioDTO();
			usuarioDto.setGerenteId(gerenteDto.getId().toString());
			usuarioDto.setUsuario(gerenteDto.getEmail());
			usuarioDto.setPerfil(gerenteDto.getCargo());
			usuarioDto.setSenha(senhaService.passwordGen());
			emailService.enviaEmailGerente(usuarioDto.getUsuario(), usuarioDto.getSenha());
			template.convertAndSend(FILA_REGISTRO_USUARIO, usuarioDto);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@RabbitListener(queues = "FILA_GERENTE_ATUALIZADO")
	public void recebeGerenteAtualizado(GerenteDTO gerenteDto) {
		try {
			System.out.print("gId: " + gerenteDto.getId() + "Email: " +gerenteDto.getEmail());
			UsuarioDTO usuarioDto = new UsuarioDTO();
			usuarioDto.setGerenteId(gerenteDto.getId().toString());
			usuarioDto.setUsuario(gerenteDto.getEmail());
			usuarioDto.setPerfil("GERENTE");
			template.convertAndSend(FILA_REGISTRO_USUARIO, usuarioDto);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@RabbitListener(queues = "FILA_GERENTE_REMOVIDO")
	public void recebeGerenteRemovido(GerenteDTO gerenteDto) {
		template.convertAndSend("FILA_DISTRIBUI_CONTAS_GERENTE",gerenteDto.getId().toString());
		System.out.print(gerenteDto.toString());
		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto.setGerenteId(gerenteDto.getId().toString());
		usuarioDto.setUsuario(gerenteDto.getEmail());
		//template.convertAndSend("FILA_REMOVE_USER", usuarioDto);
	}
}


