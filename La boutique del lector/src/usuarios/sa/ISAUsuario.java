package usuarios.sa;

import java.io.IOException;
import java.util.List;

import usuarios.Cliente;
import usuarios.Usuario;

public interface ISAUsuario {
	
	/**
	 * Agrega un usuario a la bbdd, si no existia antes
	 * @param u usuario asociado al cliente
	 * @param c cliente a agregar
	 * @throws IOException error en los archivos
	 */
	void eAltaUsuario(Usuario u, Cliente c) throws IOException;
	
	/**
	 * Elimina un usuario de la bbdd, si existia antes, y a su cliente asociado
	 * @param u dni del usuario, tambien elimina al cliente
	 * @throws IOException error en los archivos
	 */
	void eEliminarUsuario(String u) throws IOException;
	
	/**
	 * Busca un usuario dado su id en la bbdd
	 * @param u id del usuario buscado
	 * @return boolean valor booleano si lo encuentra o no
	 */
	boolean eBuscarUsuario(String u);
	
	/**
	 * Dado un dni y su cotraseña intenta iniciar sesion en el sistema
	 * @param u dni del usuario
	 * @param p su contraseña
	 * @return rol del usuario si ha conseguido iniciar sesion
	 */
	String eIniciarSesion(String u, String p);
	
	/**
	 * Devuelve toda la informacion de un usuario dado su id
	 * @param u dni de usuario
	 * @return usuario usuario encontrado
	 */
	Usuario eConsultarUsuario(String u);
	
	/**
	 * Carga todos los clientes de la bbdd
	 * @param dni dni del cliente buscado
	 * @return listClientes cliente encontrado
	 * @throws IOException error al cargar clientes
	 */
	List<Cliente> buscarCliente(String dni) throws IOException;

	/**
	 * Carga en una lista todos los clientes de la bbdd
	 * @return lista lista de clientes de la bbdd
	 * @throws IOException error en los archivos
	 */
	List<Cliente> cargarClientes()throws IOException;
	
	/**
	 * LLama al metodo verCliente de la fachada del dao de usuario
	 * @param dni dni del cliente que quieres consultar
	 */
	public Cliente verCliente(String dni);
	
	/**
	 * Actualiza la informacion de un usuario (Si existia en la bbdd)
	 * @param user usuario que quieres actualizar
	 */
	public void modificarCliente(Cliente cliente, Usuario u);
}
