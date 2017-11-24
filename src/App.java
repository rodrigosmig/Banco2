import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class App {
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		try {
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//Class.forName("com.mysql.jdbc.Driver");
			Connection conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/concessionaria?useSSL=false", "root", "rootsql");
			System.out.println("Funcionou");
		} catch (Exception e) {
			System.out.println("Não funcionou");
		}
	}
}
