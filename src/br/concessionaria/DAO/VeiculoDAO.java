package br.concessionaria.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

import br.concessionaria.modelos.Proprietario;
import br.concessionaria.modelos.Veiculo;

public class VeiculoDAO {
	
	private Connection conexao;
	
	public VeiculoDAO(DataSource dataSource) {
		this.conexao = dataSource.getConnection();
	}
	
	public void cadastrarVeic(Veiculo veiculo) throws SQLException {
		
		String SQL = "insert into veiculo (placa, modelo, ano) values (?,?,?)";
		
		PreparedStatement p = this.conexao.prepareStatement(SQL);
		
		p.setString(1, veiculo.getPlaca());
		p.setString(2, veiculo.getModelo());
		p.setString(3, veiculo.getAno());
		//p.setString(4, veiculo.getProprietario().getCpf());
		
		p.executeUpdate();
		p.close();
	}
	
	public void deletarVeic(String placa) throws SQLException {
		
		String SQL = "delete from veiculo where placa = ?";
		
		PreparedStatement p = this.conexao.prepareStatement(SQL);
		
		p.setString(1, placa);
		//p.setString(1, veiculo);
		
		//p.setInt(1, veiculo.getId());
		p.executeUpdate();
		p.close();
	}
	
	public void atualizarVeic(Veiculo veiculo) throws SQLException {
		
		System.out.println("**"+veiculo.getPlaca());
		String SQL = "update veiculo set modelo = ?, ano = ? where placa = ?";
		
		PreparedStatement p = this.conexao.prepareStatement(SQL);
		
		p.setString(1, veiculo.getModelo());
		p.setString(2, veiculo.getAno());
		p.setString(3, veiculo.getPlaca());
		p.executeUpdate();
		p.close();
	}
	
	public List<Veiculo> listarVeiculos() throws SQLException {
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		String SQL = "select*from veiculo";
		PreparedStatement p = this.conexao.prepareStatement(SQL);
		ResultSet rs = p.executeQuery();
		while(rs.next()) {
			Veiculo veiculo = new Veiculo();
			veiculo.setPlaca(rs.getString("placa"));
			veiculo.setModelo(rs.getString("modelo"));
			veiculo.setAno(rs.getString("ano"));
			veiculos.add(veiculo);
		}
		rs.close();
		p.close();
		return veiculos;
			
		}
	
	public Veiculo encontrarVeiculo(String placa) throws SQLException{
		
		Veiculo v = null;
		
		String sql = "select * from veiculo where placa = ?";
		
		PreparedStatement ps = null;
		ResultSet result = null;
		
		ps = conexao.prepareStatement(sql);
		
		ps.setString(1, placa);
		
		result = ps.executeQuery();
		
		if (result.next()) {
			
			v = new Veiculo();
			
			v.setPlaca(result.getString("placa"));
			v.setModelo(result.getString("modelo"));
			v.setAno(result.getString("ano"));
			
			DataSource datasource = new DataSource();
			ProprietarioeDAO pro = new ProprietarioeDAO(datasource);
			Proprietario p = pro.encontrarProprietario(result.getString("proprietario"));
			
			v.setProprietario(p);
		}
		
		ps.close();
		result.close();
	
		
		return v;	
		
	}
	
	public void associarProprietarioAoVeiculo(String placa, String cpf) throws SQLException {
		DataSource datasource = new DataSource();
		ProprietarioeDAO p = new ProprietarioeDAO(datasource);
		
		Proprietario proprietario = p.encontrarProprietario(cpf);
		
		Veiculo v = encontrarVeiculo(placa);		
		v.setProprietario(proprietario);
			
		String sql = "update veiculo set proprietario = ? where placa = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(sql);
		
		ps.setString(1, proprietario.getCpf());
		ps.setString(2, v.getPlaca());
		
		ps.executeUpdate();
		ps.close();

	}
	
	public ArrayList<Veiculo> listarVeiculosDeProprietario(String cpf) throws SQLException {
		DataSource datasource = new DataSource();
		ProprietarioeDAO p = new ProprietarioeDAO(datasource);
		
		ArrayList<Veiculo> lista = new ArrayList<Veiculo>();
		
		Veiculo v = null;
		
		String sql = "select * from veiculo where proprietario = ?";
		
		ResultSet result = null;
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ps.setString(1, cpf);
		
		result = ps.executeQuery();
		
		while(result.next()) {
			
			v = new Veiculo();
			
			v.setPlaca(result.getString("placa"));
			v.setModelo(result.getString("modelo"));
			v.setAno(result.getString("ano"));
			
			Proprietario proprietario = p.encontrarProprietario(result.getString("proprietario"));
			
			v.setProprietario(proprietario);
			
			lista.add(v);
		}
		
		return lista;
	}
	
}
