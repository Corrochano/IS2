package libros.dao;

import java.io.IOException;
import java.util.List;

import libros.Libro;

/**
 * Interfaz de Clase que comunica el S.A. con el DAO de libros
 */
public interface IFachadaDAOLibro {
	
	/**
	 * Guarda un nuevo libro en la bbdd (si no existia previamente)
	 * @param lib libro el cual se quiere dar de alta en la BBDD
	 * @throws IOException Error al hacer el alta del libro.
	 */
	void ejecutaAltaLibro(Libro l);
	
	
	/**
	 * Elimina un libro de la bbdd (si existia previamente)
	 * @param  id del libro que quieres eliminar de la bbdd
	 * @throws IOException Excepción lanzada si no se ha encontrado el archivo de libro con ese Id.
	 */
	void ejecutaEliminarLibro (String id);
	
	/**
	 * Busca un libro de la bbdd
	 * @param id id del libro que quieres buscar
	 * @return Libro se devuelve el libro con ese id si existe
	 * @throws IOException Excepcion lanzada si no existe ningun libro con dicha Id
	 */
	boolean ejecutaBuscarLibro (String id);
	
	/**
	 * Actualiza la informacion de un libro (Si existia en la bbdd)
	 * @param libro libro que quieres actualizar
	 * @throws IOException Error en la BBDD.
	 */
	void ejecutaModificarLibro(Libro l) throws IOException;
	
	
	/**
	 * Dado un id devuelve el libro con ese id (si existe en la bdd)
	 *@param u id del libro
	 *@return libro libro asociado a ese id
	 */
	Libro ejecutaConsultarLibro(String id);

	/**
	 * Busca el libro con el título introducido.
	 * @param tit título del libro a buscar.
	 * @return una lista de libros.
	 * @throws IOException libros.txt no encontrado
	 */
	List<Libro> ejecutaBuscarLibroTitulo(String tit) throws IOException;
	
	/**
    * Busca los libros con la categoria introducido.
    * @param cat categoria del libro a buscar.
    * @return una lista de libros.
    * @throws IOException libros.txt no encontrado
    */
   List<Libro> ejecutaBuscarLibroCat(String cat) throws IOException;

	
}
