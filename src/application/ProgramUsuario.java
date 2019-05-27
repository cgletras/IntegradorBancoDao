package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.EstadoLeilaoDao;
import model.dao.UsuarioDao;
import model.entities.EstadoLeilao;
import model.entities.Usuario;

public class ProgramUsuario {

	public static void main(String[] args) throws ParseException {
		
		// Buscar a ID na sessão
		int id = 2; 
		// Buscar a EMAIL na sessão
		String email = "willian.freitasoliveira@gmail.com";
		
		//INSERIR USUARIO
		
//OK	insereUsuario(); //parametros são instanciados dentro da função;
		
		// CARREGA USUARIO POR ID
		
//OK	carregaUsuario(id);
		
		// UPDATE USUARIO
		
//OK	atualizarUsuario(carregaUsuario(id));
		
		// LISTA TODOS OS USUARIOS RETORNA UMA LISTA
		

//OK 	listarUsuarios()) {
			
		// procura 1 usuario pelo email e retorna
			
//OK 	carregarUsuarioPorEmail(email));
		
//OK	inativaUsuarioPorId(id);

//OK	ativaUsuarioPorId(id);
		
	}

	private static void ativaUsuarioPorId(int id) {
		UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
		usuarioDao.activate(id);
	}

	private static void inativaUsuarioPorId(int id) {
		UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
		usuarioDao.inactivate(id);
	}

	private static Usuario carregarUsuarioPorEmail(String email) {
		UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
		Usuario usuario = usuarioDao.findByEmail(email);
		return usuario;
	}

	private static List<Usuario> listarUsuarios() {
		UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
		List<Usuario> list = new ArrayList<>();
		return list = usuarioDao.findAll();
		
	}

	private static Usuario atualizarUsuario(Usuario usuario) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
		
		//cerragar dados informados na pagina de atualizaçao abaixo
		
		usuario.setNome("Ricardo");
		usuario.setEmail("rrrr@gmail.com");
		usuario.setSenha("1234");
		usuario.setDataNascimento(sdf.parse("18/11/2000"));
		usuario.setAtivo(true);
		
		usuarioDao.update(usuario);
		
		return carregaUsuario(usuario.getIdUsuario());			
	}

	private static Usuario carregaUsuario(int id) {
		
		UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
		Usuario usuario = usuarioDao.findById(id);
		return usuario;
	}

	private static void insereUsuario() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
				
		Usuario usuario = new Usuario();		
		usuario.setIdUsuario(null);
		usuario.setNome("Ricardo");
		usuario.setEmail("r@gmail.com");
		usuario.setSenha("1234");
		usuario.setDataNascimento(sdf.parse("18/12/2000"));
		usuario.setAtivo(true);
		
		usuarioDao.insert(usuario);
		System.out.println("Usuario inserido com sucesso");
		
	}
}
