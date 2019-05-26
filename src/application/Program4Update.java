package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import db.DB;

public class Program4Update {

	public static void main(String[] args) {
	
	Connection conn = null;
	PreparedStatement st = null;
	
	try {
		
		conn = DB.getConnection();
		
		st = conn.prepareStatement(
		"UPDATE Departamento "
		+ "SET nome = ? "
		+ "WHERE "
		+ "cod_depto = ?");
		
		st.setString(1, "Stein Merch");
		st.setInt(2, 5);
		
		int rowsAffected = st.executeUpdate();
		System.out.println("Linhas afetadas"+ rowsAffected);
	
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	finally {
		DB.closeStatement(st);
		DB.closeConnection();
	}
		
	}
}
