package br.net.dac.saga.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {


	//private static final long serialVersionUID = 1L;
	private String id;
    private String usuario;
    private String senha;
    private String perfil;
    private String id_cliente;
    private String id_gerente;

    public UsuarioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioDTO(String id, String usuario, String senha, String perfil, String id_cliente, String id_gerente) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.perfil = perfil;
		this.id_cliente = id_cliente;
		this.id_gerente = id_gerente;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getId_gerente() {
		return id_gerente;
	}

	public void setId_gerente(String id_gerente) {
		this.id_gerente = id_gerente;
	}

	
}
