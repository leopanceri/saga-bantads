package br.net.dac.saga.dto;

public class ClienteTransfer {
	
	private ClienteDTO clienteDto;
	private String message;
	
	public ClienteTransfer() {
		
	}
	
	public ClienteTransfer(ClienteDTO clienteDto, String msg) {
		this.clienteDto = clienteDto;
		this.message = msg;
	}

	public ClienteDTO getClienteDto() {
		return clienteDto;
	}

	public void setClienteDto(ClienteDTO clienteDto) {
		this.clienteDto = clienteDto;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
