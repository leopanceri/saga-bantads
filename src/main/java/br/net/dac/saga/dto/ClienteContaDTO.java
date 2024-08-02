package br.net.dac.saga.dto;

import java.io.Serializable;

public class ClienteContaDTO implements Serializable {

	private long clienteId;
	private Double salario;
	private String email;
	private String action;

	public ClienteContaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteContaDTO(long clienteId, Double salario, String email, String action) {
		super();
		this.clienteId = clienteId;
		this.salario = salario;
		this.email = email;
		this.action = action;
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
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return action;
	}

	public void setMessage(String message) {
		this.action = message;
	}


}
