package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbIntegrityException;

public class Program5Deletar {

	public static void main(String[] args) {
	
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"DELETE FROM Departamento "
					+"WHERE "
					+"cod_depto = ?");
			st.setInt(1, 4);
			

			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas afetadas" + rowsAffected);

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
