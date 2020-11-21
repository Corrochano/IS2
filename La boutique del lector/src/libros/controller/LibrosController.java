package libros.controller;

import java.io.IOException;
import java.util.List;

import libros.Libro;
import libros.fachada.LibrosFachada;

/**
 * Clase que representa el controlador de un libro
 */
public class LibrosController {
	private LibrosFachada libros_fachada=new LibrosFachada();
	
	/**
	 * Constructora vacia
	 */
	public LibrosController() {}
	
	/**
	 * Da de alta un libro en la bbdd
	 * @param id
	 * @param titulo
	 * @param precio_alquiler
	 * @param autor
	 * @param categoria
	 * @throws IOException 
	 */
	public void altaLibro(String id, String titulo, int precio_alquiler, String autor, String categoria) throws IOException {
		libros_fachada.altaLibro(id, titulo, precio_alquiler, autor, categoria);
	}
	
	/**
	 * Inicializa la tabla con los libros
	 * @throws IOException 
	 */
	public List<Libro> cargarTodosLosLibros() throws IOException {
		return libros_fachada.cargarTodosLosLibros();
		
	}
	
	/**
	 * Elimina un libro segun su id
	 * @param id
	 * @throws IOException 
	 */
	public void eliminarLibroButton(String id) throws IOException {
		libros_fachada.eliminarLibro(id);
		
	}
	/**
	 * Actualiza un libro de la bbdd
	 * @param id
	 * @param titulo
	 * @param precio
	 * @param autor
	 * @param categoria
	 * @throws IOException 
	 */
	public void actualizarLibro(String id, String titulo, int precio, String autor, String categoria) throws IOException {
		libros_fachada.actualizarLibro(id, titulo, precio, autor, categoria);
	}
	
	/**
	 * Da la informacion de un libro
	 * @param id
	 */
	public Libro verLibro(String id) {
		return libros_fachada.verLibro(id);
	}
	
	/**
	 * Busca un libro por su categoria
	 * @param cat
	 */
	public List<Libro> buscarLibroCat(String cat) throws IOException {
		return libros_fachada.buscarLibroCat(cat);
	}
	/**
	 * Busca un libro por su titulo
	 * @param tit título a buscar
	 * @throws IOException No se ha encontrado el archivo libros.txt
	 */
	public List<Libro> buscarLibroTit(String tit) throws IOException {
		return libros_fachada.buscarLibroTit(tit);
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
