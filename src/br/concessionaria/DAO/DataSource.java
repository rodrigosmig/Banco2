package br.concessionaria.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DataSource {
	private Connection conexao;
	
	public DataSource() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			this.conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pro_veiculo?useSSL=false", "root", "rootsql");

		} catch (SQLException e) {
			System.err.println("Erro na conexão " + e.getMessage());
		}
		catch (Exception e) {
			System.err.println("Erro geral " + e.getMessage());
		}

	}
	
	public Connection getConnection(){
		return this.conexao;
	}
	
	public void closeDataSource(){
		
		try {
			conexao.close();
			
		} catch (Exception e) {
			System.err.println("Erro ao encerrar conexão " + e.getMessage());
		}
	}

	
}
