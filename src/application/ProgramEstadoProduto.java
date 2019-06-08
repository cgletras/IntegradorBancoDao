package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import model.dao.DaoFactory;
import model.dao.EstadoProdutoDao;
import model.dao.UsuarioDao;
import model.entities.EstadoProduto;
import model.entities.Usuario;

public class ProgramEstadoProduto {

	public static void main(String[] args) {
		
		
		
		//Find EstadoProduto by id
		int id = 1;
		
//OK	estadoProdutoPorId(id);
		
		//Lista todos os EstadosLeiloes.
		
// ok listarEstadosProduto();
		
				
	}

	public static List<EstadoProduto> listarEstadosProduto() {
		EstadoProdutoDao estadoProdutoDao = DaoFactory.createEstadoProdutoDao();
		return estadoProdutoDao.findAll();
	}

	public static EstadoProduto estadoProdutoPorId(int id) {
		EstadoProdutoDao estadoProdutoDao = DaoFactory.createEstadoProdutoDao();
		return estadoProdutoDao.findById(id);
	}
}
