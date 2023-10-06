package clasesDAOImplJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clasesDAO.MensajeDAO;
import clasesDeObjetosDelSistema.Mensaje;
import dataSource.MiDataSource;

public class MensajeDAOjdbc implements MensajeDAO {

	@Override
	public void guardar(Mensaje m) {
		try {
			Connection con = MiDataSource.getDataSource().getConnection();
			Statement st = con.createStatement();
			int rs = st.executeUpdate
						("INSERT INTO Mensaje(texto, email) VALUES ('"+m.getTexto()+"','"+m.getEmail()+"')"
					 );
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Mensaje> cargar() {
		List<Mensaje> lista = new ArrayList<Mensaje>();
		try {
			Connection con = MiDataSource.getDataSource().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from Mensaje");
			while (rs.next()) {
				lista.add(new Mensaje(rs.getString("texto"), rs.getString("email")));
			}
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

}
