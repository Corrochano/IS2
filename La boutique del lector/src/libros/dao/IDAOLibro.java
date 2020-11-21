package libros.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import libros.Libro;

/**
 * Clase que se encarga de transferir y actualizar la informacion de la bbdd (El txt)
 */
public interface IDAOLibro {

	/**
	 * Guarda un nuevo libro en la bbdd (si no existia previamente)
	 * @param Lib libro que quieres meter en la bbdd
	 */
	void eAltaLibro(Libro l);
	
	/**
	 * Elimina un usuario de la bbdd (si existia previamente)
	 * @param u id del libro que quieres eliminar de la bbdd
	 */
	void eEliminarLibro (String id);
	
	/**
	 * Busca un libro de la bbdd
	 * @param u id del libro que quieres buscar
	 * @return boolean de exito en la busqueda o fallo
	 */
	boolean eBuscarLibro (String id);
	
	/**
	 * Actualiza la informacion de un libro(Si existia en la bbdd)
	 * @param lib libro que quieres actualizar
	 * @throws IOException si hay un error en la BBDD.
	 */
	void eModificarLibro(Libro libro) throws IOException;
	
	/**
	 * Dado un id devuelve el libro con ese id (si existe en la bbdd)
	 *@param l id del libro
	 *@return lib libro asociado
	 */
	Libro eConsultarLibro (String id);
	
	/**
	 * Carga en una lista de libros todos los libros de la bbdd
	 * @param libros input stream
	 * @return lista lista de libros de la bbdd
	 * @throws IOException error en los archivos
	 */
	public List<Libro> cargarLibros(FileInputStream libros) throws IOException;
	
	/**
	 * Devuelve el libro correspondiente dado su id
	 * @param id id del libro buscado
	 * @return libro libro con toda su informacion
	 */
	public Libro verLibro(String id);

	/**
	 * Busca el libro con el título introducido.
	 * @param tit título del libro a buscar.
	 * @return una lista de libros.
	 * @throws IOException No se ha encontrado libro.txt
	 */
	List<Libro> eBuscarLibroTit(String tit) throws IOException;
	
	/**
	 * Guarda los datos que hay actualmente cargados en el DAO a la bbdd (libros.txt)
	 */
	public void guardarDatosLibros();
	
	/**
	 * Busca un libro dada su categoria en la bbdd
	 * @param cat categoria deseada (debe ser valida)
	 * @return lista lista de libros que pertenecen a esa categoria
	 * @throws IOException error en los archivos
	 */
	public List<Libro> eBuscarLibroCat(String cat) throws IOException;
}