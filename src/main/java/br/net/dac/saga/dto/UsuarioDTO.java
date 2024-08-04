package br.net.dac.saga.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {


	//private static final long serialVersionUID = 1L;
	private String id;
    private String usuario;
    private String senha;
    private String perfil;
    private String gerenteId;
    private String contaId;
    private String clienteId;

    public UsuarioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioDTO(String id, String usuario, String senha, String perfil, String gerenteId, String contaId,
			String clienteId) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.perfil = perfil;
		this.gerenteId = gerenteId;
		this.contaId = contaId;
		this.clienteId = clienteId;
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

	public String getGerenteId() {
		return gerenteId;
	}

	public void setGerenteId(String gerenteId) {
		this.gerenteId = gerenteId;
	}

	public String getContaId() {
		return contaId;
	}

	public void setContaId(String contaId) {
		this.contaId = contaId;
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}



}
