package usuarios.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import usuarios.Cliente;
import usuarios.Usuario;

/**
 * Clase que se encarga de transferir y actualizar la informacion de la bbdd (El txt)
 */
public interface IDAOUsuario {
	
	/**
	 * Guarda un nuevo usuario en la bbdd (si no existia previamente)
	 * @param user usuario que quieres meter en la bbdd
	 */
	void eAltaUsuario(Usuario u, Cliente c);
	
	/**
	 * Elimina un usuario de la bbdd (si existia previamente)
	 * @param u id del usuario que quieres eliminar de la bbdd
	 * @throws FileNotFoundException No se ha encontrado el fichero de usuarios y/o clientes.
	 * @throws IOException No se ha encontrado el fichero de usuarios y/o clientes.
	 */
	void eEliminarUsuario(String u) throws FileNotFoundException, IOException;
	
	/**
	 * Busca un usuario de la bbdd
	 * @param u id del usuario que quieres buscar
	 * @return boolean de exito en la busqueda o fallo
	 */
	boolean eBuscarUsuario(String u);
	
	/**
	 * Actualiza la informacion de un usuario (Si existia en la bbdd)
	 * @param user usuario que quieres actualizar
	 */
	void eModificarUsuario(Cliente c,Usuario u);
	
	/**
	 * Busca en la bbdd el usuario y devuelve su rol (en caso de que exista en la bbdd)
	 * @param u id del usuario
	 * @param p password del usuario
	 * @return rol del usuario
	 */
	String eIniciarSesion(String u, String p);
	
	/**
	 * Dado un id devuelve el usuario con ese id (si existe en la bdd)
	 *@param u id del usuario
	 *@return user usuario asociado
	 */
	Usuario eConsultarUsuario(String u);
	
	/**
	 * Carga todos los clientes de la bbdd
	 * @param dni dni del cliente buscado
	 * @return listClientes cliente encontrado
	 * @throws IOException error al cargar clientes
	 */
	public List<Cliente> eBuscarCliente(String u)throws IOException;
	
	/**
	 * Carga todos los clientes de la bbdd
	 * @param dni dni del cliente buscado
	 * @return listClientes cliente encontrado
	 * @throws IOException error al cargar clientes
	 */
	public boolean eBuscarCliente1(String u);
	
	/**
	 * Recorre el txt de clientes y devuelve el cliente con el dni deseado
	 * @param dni dni del cliente que quieres consultar
	 */
	public Cliente verCliente(String dni);
}
