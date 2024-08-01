package br.net.dac.saga.dto;

public class ClienteTransfer {

	private ClienteDTO clienteDto;
	private String message;

	public ClienteTransfer() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ClienteTransfer(ClienteDTO clienteDto, String message) {
		super();
		this.clienteDto = clienteDto;
		this.message = message;
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
