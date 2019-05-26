package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import db.DB;

public class Program3InserirDados {

	public static void main(String[] args) {
	
	SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
	
	Connection conn = null;
	
	PreparedStatement st = null;
	try {
		conn = DB.getConnection();
		
		st = conn.prepareStatement(
		"INSERT INTO Departamento"
		+"(cod_depto,nome,sigla)" 
		+"VALUES" 
		+"(?, ?, ?)");
		
		st.setInt(1, 4);
		st.setString(2, "Stein Holding");
		st.setString(3, "SHolds");
		// inserindo datas no SQL
		// 1 - Inserir o SimpleDateFormatMask
		// 2- st.setDate(3, new java.sql.Date(sdf.parse("16/11/1978").getTime()));
		
		int rowsAffected = st.executeUpdate();
		System.out.println("Done! Linhas moficadas: "+rowsAffected);
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
