package usuarios.controller;

import java.io.IOException;
import java.util.List;

import usuarios.Cliente;
import usuarios.Usuario;
import usuarios.fachada.UsuariosFachada;

/**
 * Clase que representa en controllador de un usuario
 */
public class UsuariosController {
	private UsuariosFachada fachada_usuarios;
	
	/**
	 * Constructora de Usuarios Controller, Crea una nueva fachada de usuario
	 */
	public UsuariosController() {
		fachada_usuarios = new UsuariosFachada();
	}
	/**
	 * Hace login de usuario
	 * @param user
	 * @param password
	 */
	public String login(String user, String password) {
		return fachada_usuarios.login(user,password);	
	}
	
	/**
	 * Agrega un cliente
	 * @param cliente
	 * @param usuario
	 */
	public void agregarCliente(Cliente cliente, Usuario usuario) throws IOException {
		fachada_usuarios.agregarCliente(cliente, usuario);
	}
	
	/**
	 * Busca un usuario dado su dni
	 * @param dni del cliente buscado
	 */
	public List<Cliente> buscarCliente(String dni) throws IOException{
		return fachada_usuarios.buscarCliente(dni);
	}
	
	/**
	 * Carga la lista de clientes que hay registrados en el sistema.
	 * @return La lista de clientes que hay en el sistema.
	 * @throws IOException Si no se encuentra el fichero clientes
	 */
	public List<Cliente> cargarClientes() throws IOException {
		return fachada_usuarios.cargarClientes();
		
	}
	
	/**
	 * Elimina un cliente de la base de datos.
	 * @param dni el dni del cliente que se desea eliminar
	 * @throws IOException Excepción producida si no se encuentra el fichero de usuarios y/o el de clientes.
	 */
	public void eliminarCliente(String dni) throws IOException {
		fachada_usuarios.eliminarCliente(dni);
	}
	
	/**
	 * LLama al metodo verCliente de la fachada usuario
	 * @param dni dni del cliente que quieres consultar
	 */
	public Cliente verCliente(String dni) {
		return fachada_usuarios.verCliente(dni);
	}
	
	/**
	 * Dado un id devuelve el usuario con ese id (si existe en la bdd)
	 *@param u id del usuario
	 *@return user usuario asociado
	 */
	public Usuario consultarUsuario(String u) {
		return fachada_usuarios.consultarUsuario(u);
	}
	
	/**
	 * Actualiza la informacion de un usuario (Si existia en la bbdd)
	 * @param user usuario que quieres actualizar
	 */
	public void modificarCliente(Cliente cliente, Usuario u) {
		fachada_usuarios.modificarCliente(cliente,u);
	}
}
