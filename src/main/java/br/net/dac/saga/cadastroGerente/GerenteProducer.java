package br.net.dac.saga.cadastroGerente;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.net.dac.saga.dto.GerenteDTO;

public class GerenteProducer {

	@Autowired
	private RabbitTemplate template;

	public ResponseEntity<Object> CadastroGerente(GerenteDTO gerenteDto){
		//GerenteTransfer gerenteTransfer = new GerenteTransfer(gerenteDto);
		template.convertAndSend("FILA_CRIAR_GERENTE", gerenteDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("GERENTE CADASTRADO");
	}

	public ResponseEntity<Object> AtualizaGerente(Long id, GerenteDTO gerenteDto){
		gerenteDto.setId(id);
		template.convertAndSend("FILA_ATUALIZAR_GERENTE", gerenteDto);
		return ResponseEntity.status(HttpStatus.OK).body("GERENTE ATUALIZADO");
	}

	public ResponseEntity<Object> RemoveGerente(Long id){
		//GerenteTransfer gerenteTransfer = new GerenteTransfer(gerenteDto, msg);
		Message msg = new Message(id.toString().getBytes());
		template.send("FILA_EXCLUIR_GERENTE", msg);
		return ResponseEntity.status(HttpStatus.OK).body("GERENTE REMOVIDO");
	}
}
