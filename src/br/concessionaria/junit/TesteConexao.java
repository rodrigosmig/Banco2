package br.concessionaria.junit;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.concessionaria.DAO.DataSource;
import br.concessionaria.DAO.ProprietarioeDAO;
import br.concessionaria.DAO.VeiculoDAO;
import br.concessionaria.modelos.Proprietario;
import br.concessionaria.modelos.Veiculo;

public class TesteConexao {
	
	private DataSource datasource;
	private static ProprietarioeDAO proprietarioDao;
	private static VeiculoDAO veiculoDao;
	
	@Before
	public void inicia(){
		
		datasource = new DataSource();
		proprietarioDao = new ProprietarioeDAO(datasource);
		veiculoDao = new VeiculoDAO(datasource);
		
	}
	
	/*\
	 * PROPRIETARIO
	 * 
	 * */  
	
	@Test
	public void cadastroProprietarioTest() throws SQLException {
		
		Proprietario p = new Proprietario();
		Proprietario p2 = new Proprietario();
		Proprietario p3 = new Proprietario();
		
		p.setNome("Wellington");
		p.setCpf("079465");
		p.setEndereco("Rua 18");
		p.setTelefone("5641");
		
		p2.setNome("Rodrigo");
		p2.setCpf("2255");
		p2.setEndereco("Rua 222");
		p2.setTelefone("228787");
		
		p3.setNome("Van");
		p3.setCpf("22444");
		p3.setEndereco("Rua 822");
		p3.setTelefone("220087");
		
		proprietarioDao.cadastrarProprietario(p);
		proprietarioDao.cadastrarProprietario(p2);
		proprietarioDao.cadastrarProprietario(p3);
	
	}
	
	@Test
	public void excluiProprietarioTest() throws SQLException{
		
		proprietarioDao.excluirProprietario("22444");
	
	}
	
	@Test
	public void editarProprietarioTest() throws SQLException{
		
		Proprietario p2 = proprietarioDao.encontrarProprietario("2255");
		
		p2.setNome("Airton");
		p2.setEndereco("Rua 0000");
		p2.setTelefone("987654");
		
		proprietarioDao.atualizarProprietario(p2);
	}
	
	
	@Test
	public void encontrarProprietarioTest() throws SQLException{
		
		Proprietario p = proprietarioDao.encontrarProprietario("2255");
		
		System.out.println(p);
	}
	
	@Test
	public void listarProprietarioTest() throws SQLException{
		
		proprietarioDao.listarProprietarios();
	}
	
	
	/*
	 * 	VEICULO
	 * 
	 * */
	
	@Test
	public void cadastraVeiculoTest() throws SQLException {
		
		Veiculo v1 = new Veiculo();
		
		//Proprietario p = proprietarioDao.encontrarProprietario("2255");
		
		//proprietarioDao.cadastrarProprietario(p1);
		
		v1.setAno("2017");
		v1.setPlaca("AYS5874");
		v1.setModelo("Camaro");
		//v1.setProprietario(p);
		
		veiculoDao.cadastrarVeic(v1);
		
	}
	
	@Test
	public void excluirVeiculoTest() throws SQLException{
		
		//Veiculo v = veiculoDao.encontrarVeiculo();
		
		veiculoDao.deletarVeic("QWQ89");
	
	}
	
	@Test
	public void editarVeiculoTest() throws SQLException{
		
		Veiculo v = veiculoDao.encontrarVeiculo("CHA5447");
		  
		v.setModelo("Fiat Palio");
		v.setAno("2011");

		veiculoDao.atualizarVeic(v);
	}
	
	
	@Test
	public void listarVeiculosTest() throws SQLException{
		
		//Veiculo v1 = new Veiculo();
		
		veiculoDao.listarVeiculos();				
	}
	
	@Test
	public void associarProprietarioAoVeiculoTest() {
		try {
			veiculoDao.associarProprietarioAoVeiculo("AYS5874", "2255");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void listarVeiculosDeProprietarioTest() {
		try {
			ArrayList<Veiculo> veiculos = veiculoDao.listarVeiculosDeProprietario("2255");
			
			for(Veiculo v: veiculos) {
				System.out.println(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
