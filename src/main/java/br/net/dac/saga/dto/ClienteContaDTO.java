package br.net.dac.saga.dto;

public class ClienteContaDTO {
	
	private long clienteId;
	private Double salario;
	private String message;
	
	public ClienteContaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteContaDTO(long clienteId, Double salario, String message) {
		super();
		this.clienteId = clienteId;
		this.salario = salario;
		this.message = message;
	}

	public long getClienteId() {
		return clienteId;
	}

	public void setClienteId(long clienteId) {
		this.clienteId = clienteId;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
