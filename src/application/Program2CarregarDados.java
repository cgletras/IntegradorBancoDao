package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import model.entities.CarregarDados;

public class Program2CarregarDados {

	public static void main(String[] args) {

		CarregarDados dados = new CarregarDados();
		dados.carregarDados();
	}

}
