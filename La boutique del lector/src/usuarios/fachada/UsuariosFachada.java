package usuarios.fachada;

import java.io.IOException;
import java.util.List;

import usuarios.Cliente;
import usuarios.Usuario;
import usuarios.sa.SAUsuario;

/**
 * Clase que representa a la fachada de un Usuario
 */
public class UsuariosFachada {
	private SAUsuario sa_usuarios;
	
	/**
	 * Constructora de la fachada de usuario, crea un nuevo servicio de aplicacion de usuario
	 */
	public UsuariosFachada() {
		sa_usuarios = new SAUsuario();
	}

	/**
	 * Realiza el metodo login de un usuario dada su contraseña y dni 
	 * @param dni dni
	 * @param password contraseña
	 * @return rol del usuario
	 */
	public String login(String dni, String password) {
		return sa_usuarios.eIniciarSesion(dni,password);	
	}
	
	/**
	 * Agrega un cliente la bbdd, si no existe
	 * @param cliente cliente a agregar
	 * @param usuario usuario asociado al cliente
	 * @throws IOException error en los archivos
	 */
	public void agregarCliente(Cliente cliente, Usuario usuario) throws IOException{
		sa_usuarios.eAltaUsuario(usuario, cliente);
	}

	/**
	 * Busca un cliente en la bbd dado su dni
	 * @param dni dni del cliente buscado
	 * @return lista lista(solo contiene un cliente)
	 * @throws IOException error en los archivos
	 */
	public List<Cliente> buscarCliente(String dni) throws IOException {
		return sa_usuarios.buscarCliente(dni);
	}

	/**
	 * Carga todos los clientes de la bbdd en una lista
	 * @return lista lista de los clientes de la bbdd
	 * @throws IOException error en os archivos
	 */
	public List<Cliente> cargarClientes() throws IOException{
		return sa_usuarios.cargarClientes();
	}

	/**
	 * Elimina un cliente de la BBDD
	 * @param dni dni del cliente a eliminar
	 * @throws IOException Excepción producida si no se encuentra el fichero de usuarios y/o el de clientes.
	 */
	public void eliminarCliente(String dni) throws IOException {
		sa_usuarios.eEliminarUsuario(dni);
	}
	
	/**
	 * LLama al metodo verCliente de la fachada usuario
	 * @param dni dni del cliente que quieres consultar
	 */
	public Cliente verCliente(String dni) {
		return sa_usuarios.verCliente(dni);
	}

	/**
	 * Dado un id devuelve el usuario con ese id (si existe en la bdd)
	 *@param u id del usuario
	 *@return user usuario asociado
	 */
	public Usuario consultarUsuario(String u) {
		return sa_usuarios.eConsultarUsuario(u);
	}

	/**
	 * Actualiza la informacion de un usuario (Si existia en la bbdd)
	 * @param user usuario que quieres actualizar
	 */
	public void modificarCliente(Cliente cliente, Usuario u) {
		sa_usuarios.modificarCliente(cliente,u);
	}
}
