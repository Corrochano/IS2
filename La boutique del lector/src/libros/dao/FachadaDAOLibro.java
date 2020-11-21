package libros.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import libros.Libro;

/**
 * Clase que comunica la SA con el DAO de libro.
 */
public class FachadaDAOLibro implements IFachadaDAOLibro{
	private DAOLibro _daolibro;
	
	/**
	 * Constructora con parámetro.
	 * @param d dao de libro.
	 */
	public FachadaDAOLibro (DAOLibro d) {
		_daolibro = d;
	}

	@Override
	public void ejecutaAltaLibro(Libro l){
		_daolibro.eAltaLibro(l);
	}

	@Override
	public void ejecutaEliminarLibro(String id) {
		this._daolibro.eEliminarLibro(id);
		
	}

	@Override
	public boolean ejecutaBuscarLibro(String id) {
		return this._daolibro.eBuscarLibro(id);
	}

	@Override
	public void ejecutaModificarLibro(Libro l) throws IOException {
		this._daolibro.eModificarLibro(l);
		
	}

	@Override
	public Libro ejecutaConsultarLibro(String id) {
		
		return this._daolibro.eConsultarLibro(id);
	}

	@Override
	public List<Libro> ejecutaBuscarLibroTitulo(String tit) throws IOException {
		return _daolibro.eBuscarLibroTit(tit);
	}

	@Override
	public List<Libro> ejecutaBuscarLibroCat(String cat) throws IOException {
		return _daolibro.eBuscarLibroCat(cat);
	}
	
	/**
	 * Carga en una lista de libros todos los libros de la bbdd perteneciantes a libros.txt
	 * @return lista lista de libros de la bbdd
	 * @throws IOException error en los archivos
	 */
	public List<Libro> cargarLibros() throws IOException {
		FileInputStream libros = null;
		libros = new FileInputStream("ficheros/libros.txt");
		return _daolibro.cargarLibros(libros);
		
	}

	/**
	 * Dado un id de libro, se busca el libro en la bbdd devolviendo toda su informacion
	 * @param id id del libro
	 * @return libro libro deseado
	 */
	public Libro verLibro(String id) {
		return _daolibro.verLibro(id);
	}
}
