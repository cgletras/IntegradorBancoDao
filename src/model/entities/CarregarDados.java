package model.entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class CarregarDados {

	public void carregarDados(){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try
		{
			conn = DB.getConnection();

			st = conn.createStatement();

			rs = st.executeQuery("select * from Estado_produto");

			while (rs.next()) {
				System.out.println(rs.getInt("id_estado_produto") + " - " + rs.getString("estado"));
			}
		}catch(
		SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
