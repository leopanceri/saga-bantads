package br.net.dac.saga.dto;

import java.io.Serializable;

public class GerenteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String cargo;  //"GERENTE" ou "ADMIN"

    public GerenteDTO() {}

    public GerenteDTO(Long id, String nome, String email, String cpf, String telefone, String cargo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cargo = cargo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
    
    
}
