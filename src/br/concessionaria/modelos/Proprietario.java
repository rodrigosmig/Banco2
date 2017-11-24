package br.concessionaria.modelos;

import java.io.Serializable;

public class Proprietario implements Serializable {

	private String nome;
	private String endereco;
	private String cpf;
	private String telefone;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
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
	
	public String toString() {
		return "Nome: "+ this.nome + 
				"\nEndereço: "+this.endereco +
				"\nCPF: "+this.cpf +
				"\nTelefone: "+this.telefone;
	}
	
	
	
}
