package model.entities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Transaction {

	public static void main(String[] args) {
	
		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			
			
			// Começa a transação
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE cargo Set nome='Gerente Contábil' " + "WHERE nome='Contador'");
			
		//	int x = 1;
		//	if (x<2) {
		//		throw new SQLException("Fake Error");
		//	}
			
			int rows2 = st.executeUpdate("UPDATE cargo Set nome='Contador'" + "WHERE nome='Gerente Contábil'");
			
			conn.commit();
			// Acaba a transação
						
			System.out.println("linha1"+rows1);
			System.out.println("linha2"+rows2);
			
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Uma das operações falhou: "+e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Falhou o rollback");
			}
			
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
