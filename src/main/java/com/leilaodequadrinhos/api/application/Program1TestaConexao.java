package com.leilaodequadrinhos.api.application;

import com.leilaodequadrinhos.api.db.DB;

import java.sql.Connection;

public class Program1TestaConexao {

	public static void main(String[] args) {

		Connection conn = DB.getConnection();
		DB.closeConnection();
	}

}
