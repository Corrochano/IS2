package usuarios.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import usuarios.Cliente;
import usuarios.Usuario;

/**
 * Clase que se encarga de transferir y actualizar la informacion de la bbdd (El txt)
 *
 */
public class DAOUsuario implements IDAOUsuario{

	private List<Usuario> lista_usuarios;
	private List<Cliente> lista_clientes;
	
	/**
	 * Constructora sin parámetros.
	 */
	public DAOUsuario() {
		lista_usuarios = new ArrayList<Usuario>();
		lista_clientes = new ArrayList<Cliente>();
	}
	
	
	@Override
	public void eAltaUsuario(Usuario u, Cliente c) {
		if(!eBuscarUsuario(u.getUser()) && !eBuscarCliente1(c.getDNI())) {
			lista_usuarios.add(u);
			lista_clientes.add(c);
			guardarDatosUsuarios();
			guardarDatosClientes();
		}
	}

	/**
	 * Elimina un usuario de la bbdd (si existia previamente) y tambien de la lista de clientes con su dni
	 * @param u id del usuario que quieres eliminar de la bbdd
	 * @throws IOException No se ha encontrado el fichero de usuarios y/o clientes.
	 */
	@Override
	public void eEliminarUsuario(String u) throws IOException {
		FileInputStream usuarios = null;
		usuarios = new FileInputStream("ficheros/usuarios.txt");
		lista_usuarios = cargarUsuarios(usuarios);
		
		boolean encontrado = false;
		Usuario ret = null;
		int i = 0;
		while(!encontrado && i < lista_usuarios.size()) {
			if(0 == u.compareTo(lista_usuarios.get(i).getUser())) {
				encontrado = true;
				ret = lista_usuarios.get(i);
			}
			i++;
		}
		this.lista_usuarios.remove(ret);
		
		FileInputStream clientes = null;
		clientes = new FileInputStream("ficheros/clientes.txt");
		lista_clientes = cargarClientes(clientes);
		
		encontrado = false;
		Cliente ret2 = null;
		i = 0;
		
		while(!encontrado && i < lista_clientes.size()) {
			if(0 == u.compareTo(lista_clientes.get(i).getDNI())) {
				encontrado = true;
				ret2 = lista_clientes.get(i);
			}
			i++;
		}
		this.lista_clientes.remove(ret2);
		
		guardarDatosUsuarios();
		guardarDatosClientes();
	}

	/**
	 * Busca un usuario de la bbdd
	 * @param u id del usuario que quieres buscar
	 * @return boolean de exito en la busqueda o fallo
	 */
	@Override
	public boolean eBuscarUsuario(String u) {
		FileInputStream usuarios = null;
		try {
			usuarios = new FileInputStream("ficheros/usuarios.txt");
			lista_usuarios = cargarUsuarios(usuarios);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Usuario user: lista_usuarios) {
			if(u.equalsIgnoreCase(user.getUser())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Busca un cliente de la bbdd
	 * @param u id del cliente que quieres buscar
	 * @return boolean de exito en la busqueda o fallo
	 */
	@Override
	public boolean eBuscarCliente1(String cl) {
		FileInputStream clientes = null;
		try {
			clientes = new FileInputStream("ficheros/clientes.txt");
			lista_clientes = cargarClientes(clientes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Cliente c: lista_clientes) {
			if(cl.equalsIgnoreCase(c.getDNI())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Actualiza la informacion de un usuario (Si existia en la bbdd)
	 * @param user usuario que quieres actualizar
	 */
	@Override
	public void eModificarUsuario(Cliente c,Usuario u) {
		if(eBuscarUsuario(u.getUser())) {
			try {
				eEliminarUsuario(u.getUser());
			} catch (IOException e) {
				e.printStackTrace();
			}
			eAltaUsuario(u,c);
			guardarDatosUsuarios();
			guardarDatosClientes();
		}
	}

	/**
	 * Busca en la bbdd el usuario y devuelve su rol (en caso de que exista en la bbdd)
	 * @param u id del usuario
	 * @param p password del usuario
	 * @return rol del usuario
	 */
	@Override
	public String eIniciarSesion(String u, String p) {
		FileInputStream usuarios = null;
		try {
			usuarios = new FileInputStream("ficheros/usuarios.txt");
			lista_usuarios = cargarUsuarios(usuarios);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Usuario user: lista_usuarios) {
			if(u.equals(user.getUser()) && p.equals(user.getPass())) {
				return user.getRol();
			}
		}
		return null;
	}

	/**
	 * Dado un id devuelve el usuario con ese id (si existe en la bdd)
	 *@param u id del usuario
	 *@return user usuario asociado
	 */
	@Override
	public Usuario eConsultarUsuario(String u) {
		FileInputStream usuarios = null;
		try {
			usuarios = new FileInputStream("ficheros/usuarios.txt");
			lista_usuarios = cargarUsuarios(usuarios);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Usuario user: lista_usuarios) {
			if(u.equals(user.getUser())) {
				return user;
			}
		}
		return null;
	}

	
	
	/**
	 * Carga los datos del fichero 'usuarios.txt'
	 * @param file archivo de carga de usuarios
	 * @return lista_usuarios devuelve una lista de usuarios
	 * @throws IOException excepcion de Input o Output
	 */
	public List<Usuario> cargarUsuarios(InputStream file) throws IOException{
	
		List<Usuario>  lista_usuarios = new ArrayList<Usuario>();
		
		String line = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(file));
		 
				 while((line = br.readLine()) != null){
					 String[] w = line.split(" ");
						 if(w.length == 3){
							 lista_usuarios.add(new Usuario(w[0], w[1], w[2]));
						 }
						 
					 }
					 			 	
				 br.close();
		
		return lista_usuarios;
		
	}
	
	/**
	 * Carga los datos del fichero 'clientes.txt'
	 * @param file fichero de carga de clientes
	 * @return lista de clientes de la aplicacion
	 * @throws IOException excepcion de Input o Output
	 */
	public List<Cliente> cargarClientes(InputStream file) throws IOException{
		List<Cliente> lista_clientes = new ArrayList<Cliente>();
		
		 String line = "";
		 BufferedReader br = new BufferedReader(new InputStreamReader(file));
		 
				 while((line = br.readLine()) != null){
					 String[] w = line.split(" ");
						 if(w.length == 4){
							 w[1] = w[1].replace("_"," ");
							 lista_clientes.add(new Cliente(w[0],w[1],w[2],w[3])); 
						 }
						 
					 }
					 			 	
				 br.close();
		
		
		return lista_clientes;
	}
	
	/**
	 * Método para escribir en el archivo de usuarios los usuarios que hay en la lista de usuarios
	 */
	private void guardarDatosUsuarios() {
		
		FileOutputStream file = null;
		try {
			file = new FileOutputStream("ficheros/usuarios.txt");
			BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(file));
				
				for(int i=0 ; i < lista_usuarios.size(); i++){
					buffer.write(lista_usuarios.get(i) + "\n");
				}
				
				buffer.close();
				
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
	/**
	 * Método para escribir en el archivo de usuarios los usuarios que hay en la lista de usuarios
	 */
	private void guardarDatosClientes() {
		
		FileOutputStream file = null;
		try {
			file = new FileOutputStream("ficheros/clientes.txt");
			BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(file));
				
				for(int i=0 ; i < lista_clientes.size(); i++){
					buffer.write(lista_clientes.get(i) + "\n");
				}
				
				buffer.close();
				
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public List<Cliente> eBuscarCliente(String u) throws IOException {
		FileInputStream clientes = null;
		List<Cliente>aux=new ArrayList<Cliente>();
		clientes = new FileInputStream("ficheros/clientes.txt");
		lista_clientes = cargarClientes(clientes);
		for(Cliente c: lista_clientes) {
			if(u.equalsIgnoreCase(c.getDNI())) {
				aux.add(c);
			}
		}
		return aux;
	}


	@Override
	public Cliente verCliente(String dni) {
		try {
			return eBuscarCliente(dni).get(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
