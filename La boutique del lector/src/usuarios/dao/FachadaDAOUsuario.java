package usuarios.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import usuarios.Cliente;
import usuarios.Usuario;

/**
 * Interfaz de Clase que comunica el S.A. con el DAO de usuario
 */
public class FachadaDAOUsuario implements IFachadaDAOUsuario{

	private DAOUsuario _daousuario;
	
	/**
	 * Constructora de la fachada del dao de usuario, asigna el dao pasado como parametro
	 * @param d dao de la fachada del dao.
	 */
	public FachadaDAOUsuario(DAOUsuario d) {
		_daousuario = d;
	}

	@Override
	public void AltaUsuario(Usuario u, Cliente c) {
		_daousuario.eAltaUsuario(u,c);
	}

	@Override
	public void EliminarUsuario(String u) throws IOException {
		_daousuario.eEliminarUsuario(u);
	}

	@Override
	public boolean BuscarUsuario(String u) {
		return _daousuario.eBuscarUsuario(u);
	}

	@Override
	public void ModificarUsuario(Cliente c,Usuario u) {
		_daousuario.eModificarUsuario(c,u);
	}

	@Override
	public String IniciarSesion(String u, String p) {
		return _daousuario.eIniciarSesion(u, p);
	}

	@Override
	public Usuario ConsultarUsuario(String u) {
		return _daousuario.eConsultarUsuario(u);
	}

	@Override
	public List<Cliente> buscarCliente(String dni) throws IOException{
		return _daousuario.eBuscarCliente(dni);
	}

	@Override
	public List<Cliente> cargarClientes()throws IOException{
		FileInputStream clientes = null;
		clientes = new FileInputStream("ficheros/clientes.txt");
		return _daousuario.cargarClientes(clientes);
		
	}

	@Override
	public Cliente verCliente(String dni) {
		return _daousuario.verCliente(dni);
	}
	
}
