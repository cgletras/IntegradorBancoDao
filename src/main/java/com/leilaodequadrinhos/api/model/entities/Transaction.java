package com.leilaodequadrinhos.api.model.entities;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaction {

	public static void main(String[] args) {
	
		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			
			
			// Come�a a transa��o
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE cargo Set nome='Gerente Cont�bil' " + "WHERE nome='Contador'");
			
		//	int x = 1;
		//	if (x<2) {
		//		throw new SQLException("Fake Error");
		//	}
			
			int rows2 = st.executeUpdate("UPDATE cargo Set nome='Contador'" + "WHERE nome='Gerente Cont�bil'");
			
			conn.commit();
			// Acaba a transa��o
						
			System.out.println("linha1"+rows1);
			System.out.println("linha2"+rows2);
			
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Uma das opera��es falhou: "+e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Falhou o rollback");
			}
			
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
