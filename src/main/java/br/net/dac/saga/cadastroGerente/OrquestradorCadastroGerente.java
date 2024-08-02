package br.net.dac.saga.cadastroGerente;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.net.dac.saga.dto.GerenteDTO;
import br.net.dac.saga.dto.UsuarioDTO;

@Component
public class OrquestradorCadastroGerente {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private GerenteProducer gerenteProducer;

	@RabbitListener(queues= "FILA_GERENTE_CADASTRADO")
	public void recebeGerenteCadastrado(GerenteDTO gerenteDto) {
		try {
			System.out.print("gId: " + gerenteDto.getId() + "Email: " +gerenteDto.getEmail());
			template.convertAndSend("FILA_ATRIBUI_CONTA_GERENTE", gerenteDto.getId().toString());
			UsuarioDTO usuarioDto = new UsuarioDTO();
			usuarioDto.setId_gerente(gerenteDto.getId().toString());
			usuarioDto.setUsuario(gerenteDto.getEmail());
			usuarioDto.setPerfil("GERENTE");
			usuarioDto.setSenha("123456");
			template.convertAndSend("fila-test", usuarioDto);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	@RabbitListener(queues = "FILA_GERENTE_ATUALIZADO")
	public void recebeGerenteAtualizado(GerenteDTO gerenteDto) {
		try {
			System.out.print("gId: " + gerenteDto.getId() + "Email: " +gerenteDto.getEmail());
			UsuarioDTO usuarioDto = new UsuarioDTO();
			usuarioDto.setId_gerente(gerenteDto.getId().toString());
			usuarioDto.setUsuario(gerenteDto.getEmail());
			usuarioDto.setPerfil("GERENTE");
			template.convertAndSend("fila-test", usuarioDto);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RabbitListener(queues = "FILA_GERENTE_REMOVIDO")
	public void recebeGerenteRemovido(GerenteDTO gerenteDto) {
		template.convertAndSend(gerenteDto.getId().toString());
		System.out.print(gerenteDto.toString());
		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto.setId_gerente(gerenteDto.getId().toString());
		usuarioDto.setUsuario(gerenteDto.getEmail());
		//template.convertAndSend("FILA_REMOVE_USER", usuarioDto);
	}
}

	
