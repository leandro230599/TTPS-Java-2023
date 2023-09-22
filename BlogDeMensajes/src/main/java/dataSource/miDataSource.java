package dataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class miDataSource {
	
	private static miDataSource dataSource = null;
	
	static {
		Connection con;
		try {
			Context ctx = (Context) (new InitialContext().lookup("java:comp/env"));
			DataSource sc = (DataSource)ctx.lookup("jdbc/MySQLDS");
			con = sc.getConnection();
			Statement st = con.createStatement();
			ResultSet rs= st.executeQuery("Select * from Usuarios");
			
			/*dataSource = (miDataSource)new InitialContext().lookup("java:comp/env/jdbc/MySQLDS");
			// Establecer la conexión
            Connection conexion = dataSource.getConnection();*/

            if (con != null) {
    			System.out.println("Se realizo la conexion");
                con.close();
            } else {
    			System.out.println("No se pudo conectar");
            }
	
		} catch (NamingException e) {
			System.out.println("Error de Nombre");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error de SQLEXCEPTION");
		}
	}
	public static miDataSource getDataSource(){
		return dataSource;
	}
	
	
}
