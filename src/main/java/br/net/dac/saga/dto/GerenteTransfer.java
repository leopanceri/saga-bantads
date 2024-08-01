package br.net.dac.saga.dto;

public class GerenteTransfer {

	private GerenteDTO gerenteDto;
	private String message;

	public GerenteTransfer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GerenteTransfer(GerenteDTO gerenteDto, String message) {
		super();
		this.gerenteDto = gerenteDto;
		this.message = message;
	}
	public GerenteDTO getGerenteDto() {
		return gerenteDto;
	}
	public void setGerenteDto(GerenteDTO gerenteDto) {
		this.gerenteDto = gerenteDto;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}



}
