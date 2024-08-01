package br.net.dac.saga.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.net.dac.saga.autocadastro.ClienteProducer;
import br.net.dac.saga.cadastroGerente.GerenteProducer;
import br.net.dac.saga.dto.ClienteDTO;
import br.net.dac.saga.dto.GerenteDTO;

@CrossOrigin
@RestController
public class SagaRest {

	@Autowired
	private ClienteProducer clienteProducer;

	@Autowired
	private GerenteProducer gerenteProducer;


	@PostMapping("/cadastro")
	public ResponseEntity<Object> saveCliente(@RequestBody ClienteDTO clienteDto){
		return clienteProducer.enviaCliente(clienteDto, "CRIAR");
	}

	@PutMapping("/cliente/update")
	public ResponseEntity<Object> alteraCadastro(@RequestBody ClienteDTO clienteDto) {
		return clienteProducer.enviaCliente(clienteDto, "ATUALIZAR");
	}

	@PostMapping("/cadastro-gerente")
	public ResponseEntity<Object> cadastroNovoGerente(@RequestBody GerenteDTO gerenteDto){
		return gerenteProducer.CadastroGerente(gerenteDto);
	}

	@PutMapping("/gerente/update")
	public ResponseEntity<Object> atualizaGerente(@RequestBody GerenteDTO gerenteDto){
		return gerenteProducer.AtualizaGerente(gerenteDto);
	}

	@DeleteMapping("/excluir-gerente/{id}")
	public ResponseEntity<Object> exclirGerente(@PathVariable long id){
		return gerenteProducer.RemoveGerente(id);
	}
}
