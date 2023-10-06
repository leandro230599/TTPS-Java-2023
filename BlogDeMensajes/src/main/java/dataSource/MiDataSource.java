package dataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MiDataSource {
	
	private static DataSource dataSource = null;
	
	static {
		try {
			dataSource = (DataSource)new InitialContext().lookup("java:comp/env/jdbc/MySQLDS");
		} catch (NamingException e) {
			System.out.println("Error de Nombre");
		}
	}
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	
}
