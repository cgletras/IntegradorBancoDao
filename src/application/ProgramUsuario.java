package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class ProgramUsuario {

	public static void main(String[] args) throws ParseException {
		
		// Buscar a ID na sessao
		int id = 2; 
		// Buscar a EMAIL na sess�o
		String email = "willian.freitasoliveira@gmail.com";
		
		//INSERIR USUARIO
		
//OK	insereUsuario(); //parametros s�o instanciados dentro da fun��o;
		
		// CARREGA USUARIO POR ID
		
//OK	carregaUsuario(id);
		
		// UPDATE USUARIO
		
//OK	atualizarUsuario(carregaUsuario(id));
		
		// LISTA TODOS OS USUARIOS RETORNA UMA LISTA
		

//OK 	listarUsuarios();
			
		// procura 1 usuario pelo email e retorna
			
// ok carregarUsuarioPorEmail("cgletras@gmail.com"));
		
//OK	inativaUsuarioPorId(id);

//OK	ativaUsuarioPorId(id);
		
		
		
// ok		login("cgletras@gmail.com"); 
		
	}

	public static void login(String email) {
		User user = carregarUsuarioPorEmail(email);
		
		if (user == null) {
			System.out.println("Usuario ou senha incorretos");
		} else if (user.getPassword().equals("cg2468")){
			System.out.println(user);
		} else {
			System.out.println("Usuario ou senha incorreta");
		}
	}

	public static void ativaUsuarioPorId(int id) {
		UserDao userDao = DaoFactory.createUsuarioDao();
		userDao.activate(id);
	}

	public static void inativaUsuarioPorId(int id) {
		UserDao userDao = DaoFactory.createUsuarioDao();
		userDao.inactivate(id);
	}

	public static User carregarUsuarioPorEmail(String email) {
		UserDao userDao = DaoFactory.createUsuarioDao();
		User user = userDao.findByEmail(email);
		return user;
	}

	public static List<User> listarUsuarios() {
		UserDao userDao = DaoFactory.createUsuarioDao();
		List<User> list = new ArrayList<>();
		return list = userDao.findAll();
		
	}

	public static User atualizarUsuario(User user) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		UserDao userDao = DaoFactory.createUsuarioDao();
		
		//carregar dados informados na pagina de atualiza�ao abaixo
		
		user.setName("Ricardo");
		user.setEmail("rrrr@gmail.com");
		user.setPassword("1234");
		user.setDateOfBirth(sdf.parse("18/11/2000"));
		user.setStatus(true);
		
		userDao.update(user);
		
		return carregaUsuario(user.getUserID());
	}

	public static User carregaUsuario(int id) {
		
		UserDao userDao = DaoFactory.createUsuarioDao();
		User user = userDao.findById(id);
		return user;
	}

	public static void insereUsuario() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		UserDao userDao = DaoFactory.createUsuarioDao();
				
		User user = new User();
		user.setUserID(null);
		user.setName("Ricardo");
		user.setEmail("r@gmail.com");
		user.setPassword("1234");
		user.setDateOfBirth(sdf.parse("18/12/2000"));
		user.setStatus(true);
		
		userDao.insert(user);
		System.out.println("User inserido com sucesso");
	}
}
