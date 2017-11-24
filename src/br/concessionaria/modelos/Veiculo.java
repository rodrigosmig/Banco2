package br.concessionaria.modelos;

public class Veiculo {

	private String placa;
	private String modelo;
	private String ano;	
	private Proprietario proprietario;
	
	public Veiculo() {
	
	
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getAno() {
		return ano;
	}
	
	public void setAno(String ano) {
		this.ano = ano;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}
	
	public String toString(){
		return this.modelo;
	}
	
}
