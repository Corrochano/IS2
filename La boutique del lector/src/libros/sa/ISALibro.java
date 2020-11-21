package libros.sa;

import java.io.IOException;
import java.util.List;

import libros.Libro;

/**
 * Interfaz del sevicio de aplicacion de un libro
 */
public interface ISALibro {
	
	/**
	 * LLama a la DAO para que guarde el libro en la BBDD
	 * @param user usuario que quieres meter en la bbdd
	 * @throws IOException El ID del libro ya existe.
	 */
	void eAltaLibro(Libro libro) throws IOException;
	
	/**
	 * Llama al DAO para que elimine de la BBDD el libro con el siguiente id
	 * @param  id del libro que quieres eliminar de la bbdd
	 * @throws IOException Excepción lanzada si no se ha encontrado el archivo de libro con ese Id.
	 */
	void eEliminarLibro(String u) throws IOException;
	
	/**
    * Llama a la DAO para saber si exite el libro con ese id
    * @param id id del libro que quieres buscar
    * @return true si existe el libro o false de lo contrario
    */
	boolean eBuscarLibro(String u);
	
	/**
     * LLama al DAO para que, dado un id devuelva el libro con ese id (si existe en la bdd)
     *@param u id del libro
     *@return libro libro asociado a ese id
	 * @throws IOException error en los archivos
     */
	Libro eConsultarLibro(String u) throws IOException;
	
	/**
	 * Carga todos los libros de la bbdd
	 * @param id id del libro buscado
	 * @return listLibro libro encontrado
	 * @throws IOException error al cargar libros
	 */
	List<Libro> cargarLibros()throws IOException;
	
	/**
	 * LLama al metodo verLibro de la fachada del dao de libro
	 * @param id id del libro que quieres consultar
	 */
	public Libro verLibro(String id);
	
	/**
	 * Actualiza la informacion de un libro (Si existia en la bbdd)
	 * @param libro libro que quieres actualizar
	 * @throws IOException Error en la BBDD
	 */
	public void modificarLibro(Libro libro) throws IOException;
	
	/**
	 * Devuelve los libros con el título buscado
	 * @param tit título del libro a buscar
	 * @return una lista de libros
	 * @throws IOException No se ha podido cargar libros.txt
	 */
	List<Libro> eBuscarLibroTitulo(String tit) throws IOException;
	
	/**
    * Devuelve los libros con la categoria buscada
    * @param cat categoria del libro a buscar
    * @return una lista de libros
    * @throws IOException No se ha podido cargar libros.txt
    */
   List<Libro> eBuscarLibroCategoria(String cat) throws IOException;
}
