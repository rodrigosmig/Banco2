package br.concessionaria.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.concessionaria.modelos.Proprietario;

public class ProprietarioeDAO {
	
	private Connection conexao;
	
	public ProprietarioeDAO(DataSource dataSource) {
		
		this.conexao = dataSource.getConnection();		
	}
	
	/*
	 * listando  proprietarios
	 * 
	 * */
	public ArrayList<Proprietario> listarProprietarios() throws SQLException {
		String sql = "select * from proprietario;";
		PreparedStatement ps = this.conexao.prepareStatement(sql);
		ResultSet result = ps.executeQuery();
		
		ArrayList<Proprietario> proprietarios = new ArrayList<Proprietario>();
		
		while(result.next()) {
			Proprietario p = new Proprietario();
			
			p.setNome(result.getString("nome"));
			p.setCpf(result.getString("cpf"));
			p.setTelefone(result.getString("telefone"));
			p.setEndereco(result.getString("endereco"));
			
			proprietarios.add(p);
		}
		result.close();
		ps.close();
		
		return proprietarios;
	}
	
	/*
	 * Cadastrando um proprietario
	 * 
	 * */
	public void cadastrarProprietario(Proprietario p) throws SQLException{
		
		
		try {
			
			String sql = "insert into proprietario (nome, cpf, endereco, telefone) values (?,?,?,?)";
			
			// cria a conexão
			PreparedStatement ps = this.conexao.prepareStatement(sql); 
			
			ps.setString(1, p.getNome());
			ps.setString(2, p.getCpf());
			ps.setString(3, p.getEndereco());
			ps.setString(4, p.getTelefone());
			
			ps.executeUpdate(); 
			ps.close();
			
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.err.println("Já existe propriétario com esse CPF");
		}
		
	}
	
	/*
	 * 
	 * Excluindo um proprietario pelo id
	 * 
	 * */
	
	public void excluirProprietario(String cpf) throws SQLException{
		
		String sql = "delete from proprietario where cpf = ?";
		
		// cria a conexão
		PreparedStatement ps = this.conexao.prepareStatement(sql); 
		
		ps.setString(1, cpf);
		
		ps.executeUpdate(); 
		ps.close();
	}
	
	
	/*
	 * Editando proprietario
	 * 
	 * */
	
	public void atualizarProprietario(Proprietario p) throws SQLException{
		
		// nome, cpf,endereco,telefone
		String sql = "update proprietario set nome = ?, endereco = ?, telefone = ? where cpf = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(sql); 
		
		ps.setString(1, p.getNome());
		ps.setString(2, p.getEndereco());
		ps.setString(3, p.getTelefone());
		ps.setString(4, p.getCpf());
		
		ps.executeUpdate(); 
		ps.close();
	}
	
	public Proprietario encontrarProprietario(String cpf) throws SQLException{
		
		Proprietario p = null;
		
		String sql = "select * from proprietario where cpf = ?";
		
		PreparedStatement ps = null;
		ResultSet result = null;
		
		ps = conexao.prepareStatement(sql);
		
		ps.setString(1, cpf);
		
		result = ps.executeQuery();
		
		if (result.next()) {
			
			p = new Proprietario();
			
			p.setNome(result.getString("nome"));
			p.setEndereco(result.getString("endereco"));
			p.setCpf(result.getString("cpf"));
			p.setTelefone(result.getString("telefone"));
			
			//System.out.println(p);	
			
		}
		
		ps.close();
		result.close();
	
		
		return p;
		
	}

	
}
