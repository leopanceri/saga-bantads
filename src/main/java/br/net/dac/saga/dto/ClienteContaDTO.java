package br.net.dac.saga.dto;

import java.io.Serializable;

public class ClienteContaDTO implements Serializable {

	private Long clienteId;
	private Long contaId;
	private Long idGerente;
	private Double salario;
	private String email;
	private String mensagem;


	public ClienteContaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteContaDTO(Long clienteId, Long contaId,Long idGerente, Double salario, String email, String mensagem) {
		super();
		this.clienteId = clienteId;
		this.contaId = contaId;
		this.idGerente = idGerente;
		this.salario = salario;
		this.email = email;
		this.mensagem = mensagem;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getContaId() {
		return contaId;
	}

	public void setContaId(Long contaId) {
		this.contaId = contaId;
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

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Long getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(Long idGerente) {
		this.idGerente = idGerente;
	}
	





}
