package usuarios.sa;

import java.io.IOException;
import java.util.List;

import usuarios.Cliente;
import usuarios.Usuario;
import usuarios.dao.DAOUsuario;
import usuarios.dao.FachadaDAOUsuario;

/**
 * Servicio de aplicación del subsistema de usuarios, comunica el controller de usuario con la fachada de usuario.
 */
public class SAUsuario implements ISAUsuario{

	FachadaDAOUsuario fdusuario;
	DAOUsuario du;
	
	/**
	 * Constructora sin parámetros
	 */
	public SAUsuario() {
		du = new DAOUsuario();
		fdusuario = new FachadaDAOUsuario(du);
	}
	
	@Override
	public void eAltaUsuario(Usuario u, Cliente c) throws IOException {
		if(!fdusuario.BuscarUsuario(u.getUser())) {	//No existe ese usuario (Bloque combinado)
			fdusuario.AltaUsuario(u, c);
		}else {
			throw new IOException();
		}
	}

	@Override
	public void eEliminarUsuario(String u) throws IOException {
		if(fdusuario.BuscarUsuario(u)) {	//Existe ese usuario (Bloque combinado)
			fdusuario.EliminarUsuario(u);
		}else {
			throw new RuntimeException("Usuario no existente");
		}
	}

	@Override
	public boolean eBuscarUsuario(String u) {
		return fdusuario.BuscarUsuario(u);
	}

	@Override
	public String eIniciarSesion(String u, String p) {
		String ret = fdusuario.IniciarSesion(u, p);
		if(  ret != null) {
			return ret;
		}else {
			throw new RuntimeException("Usuario no existente");
		}
	}

	@Override
	public Usuario eConsultarUsuario(String u) {
		Usuario ret = fdusuario.ConsultarUsuario(u);
		if(  ret != null) {
			return ret;
		}else {
			throw new RuntimeException("Usuario no existente");
		}
	}

	@Override
	public List<Cliente>  buscarCliente(String dni)throws IOException {
		return fdusuario.buscarCliente(dni);
	}
	
	@Override
	public List<Cliente> cargarClientes()throws IOException {
		return fdusuario.cargarClientes();
	}

	/**
	 * LLama al metodo verCliente de la fachada usuario
	 * @param dni dni del cliente que quieres consultar
	 */
	public Cliente verCliente(String dni) {
		return fdusuario.verCliente(dni);
	}

	/**
	 * Actualiza la informacion de un usuario (Si existia en la bbdd)
	 * @param user usuario que quieres actualizar
	 */
	public void modificarCliente(Cliente cliente, Usuario u) {
		fdusuario.ModificarUsuario(cliente,u);
	}

}
