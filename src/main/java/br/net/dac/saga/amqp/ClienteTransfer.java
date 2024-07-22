package br.net.dac.saga.amqp;

import java.io.Serializable;

import br.net.dac.saga.dto.ClienteDTO;

public class ClienteTransfer implements Serializable {
	ClienteDTO clienteDto;
	String action;
	String message;
	
	public ClienteTransfer() {
		
	}
	
	public ClienteTransfer(String msg, String act) {
		this.message = msg;
		this.action = act;
	}
	
	public ClienteTransfer(ClienteDTO clienteDto, String act) {
		this.clienteDto = clienteDto;
		this.action = act;
	}
	public ClienteTransfer(ClienteDTO clienteDto, String msg, String act) {
		this.clienteDto = clienteDto;
		this.message = msg;
		this.action = act;
	}

	public ClienteDTO getClienteDto() {
		return clienteDto;
	}

	public void setClienteDto(ClienteDTO clienteDto) {
		this.clienteDto = clienteDto;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
