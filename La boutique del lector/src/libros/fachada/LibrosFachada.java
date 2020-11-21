package libros.fachada;

import java.io.IOException;
import java.util.List;

import libros.CategoriaEnum;
import libros.Libro;
import libros.sa.SALibro;

/**
 * Clase que comunica el controller con el sevicio de aplicacion
 */
public class LibrosFachada {
	private SALibro sa_libro;
	
	/**
	 * Constructora de la fachada, crea un nuevo servicio de aplicacion
	 */
	public LibrosFachada() {
	 sa_libro=new SALibro();
	}

	/**
	 * Carga en una lista de libros todos los libros de la bbdd perteneciantes a libros.txt
	 * @return lista lista de libros de la bbdd
	 * @throws IOException error en los archivos
	 */
	public List<Libro> cargarTodosLosLibros() throws IOException {
		return sa_libro.cargarLibros();
		
	}

	/**
	 * Crea un nuevo libro con los parametros dados
	 * @param id id del libro
	 * @param titulo titulo del libro
	 * @param precio precio del libro
	 * @param autor autor del libro
	 * @param categoria categoria del libro
	 * @throws IOException error en los archivos
	 */
	public void altaLibro(String id, String titulo, int precio, String autor, String categoria) throws IOException{
		sa_libro.eAltaLibro(new Libro(id,titulo,precio,autor,CategoriaEnum.valueOf(categoria)));
		
	}

	/**
	 * Elimina un libro de la bbdd dado su id
	 * @param id id del libro deseado
	 * @throws IOException error en los archivos
	 */
	public void eliminarLibro(String id) throws IOException {
		sa_libro.eEliminarLibro(id);
		
	}

	/**
	 * Busca un libro en la bbdd dado su titulo
	 * @param tit titulo buscado
	 * @return lista lista de libros con el titulo buscado (puede haber mas de uno)
	 * @throws IOException error en los archivos
	 */
	public List<Libro> buscarLibroTit(String tit) throws IOException {
		return sa_libro.eBuscarLibroTitulo(tit);
	}

	/**
	 * Busca un libro en la bbdd dada su categoria
	 * @param cat categoria deseada
	 * @return lista lista de libros de la bbdd con la categoria buscada (puede ser mas de uno)
	 * @throws IOException error en los archivos
	 */
	public List<Libro> buscarLibroCat(String cat)throws IOException {
		return sa_libro.eBuscarLibroCategoria(cat);
		
	}

	/**
	 * Devuelve el libro correspondiente dado su id
	 * @param id id del libro buscado
	 * @return libro libro con toda su informacion
	 */
	public Libro verLibro(String id) {
		return sa_libro.verLibro(id);
		
	}

	/**
	 * Actualiza los alguno o todos los campos de un libro dado su id (el id no se puede cambiar)
	 * @param id id buscado
	 * @param titulo titulo nuevo
	 * @param precio precio nuevo
	 * @param autor autor nuevo
	 * @param categoria categoria nueva
	 * @throws IOException error en los archivos
	 */
	public void actualizarLibro(String id, String titulo, int precio, String autor, String categoria) throws IOException {
		sa_libro.modificarLibro(new Libro(id,titulo,precio,autor,CategoriaEnum.valueOf(categoria)));
	}
}
