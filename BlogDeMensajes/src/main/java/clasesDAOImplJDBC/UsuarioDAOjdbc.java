package clasesDAOImplJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import clasesDAO.UsuarioDAO;
import clasesDeObjetosDelSistema.Usuario;
import dataSource.MiDataSource;

public class UsuarioDAOjdbc implements UsuarioDAO {

	@Override
	public Usuario encontrar(String email) {
		Usuario user = null;
		try {
			Connection con = MiDataSource.getDataSource().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from Usuario where u.email = '"+email+"'");
			if (rs.next()) {
				user = new Usuario();
				user.setEmail(email);
			}
			rs.close();
			st.close();
			con.close();
		}
		catch (java.sql.SQLException e) {
			 System.out.println("Error de SQL: "+e.getMessage());
			 }
			 return user;
	}

}
