package libros.sa;

import java.io.IOException;
import java.util.List;

import libros.Libro;
import libros.dao.DAOLibro;
import libros.dao.FachadaDAOLibro;

/**
 * Clase que representa el servicio de aplicacion de un libro, se comunica desde la fachada del libro a la facahda del dao
 */
public class SALibro implements ISALibro{

	FachadaDAOLibro fdlibro;
	DAOLibro dl;
	
	/**
	 * Constructora del servicio de aplicacion, crea un nuevo dao y una nueva facahda del dao
	 */
	public SALibro() {
		dl = new DAOLibro();
		fdlibro = new FachadaDAOLibro(dl);
	}
	
	@Override
	public void eAltaLibro(Libro libro) throws IOException{
		if(!fdlibro.ejecutaBuscarLibro(libro.get_id())) { //No existe ese libro (Bloque combinado)
			fdlibro.ejecutaAltaLibro(libro);
		}else {
			throw new IOException();
		}
	}

	@Override
	public void eEliminarLibro(String l) throws IOException {
		if(fdlibro.ejecutaBuscarLibro(l)) {	//Existe ese libro (Bloque combinado)
			fdlibro.ejecutaEliminarLibro(l);
		}else {
			throw new RuntimeException();
		}
		
		
	}

	@Override
	public boolean eBuscarLibro(String l) {
		return fdlibro.ejecutaBuscarLibro(l);
	}

	@Override
	public Libro eConsultarLibro(String l) throws IOException {
		Libro ret = fdlibro.ejecutaConsultarLibro(l);
		if(  ret != null) {
			return ret;
		}else {
			throw new IOException();
		}
	}

	@Override
	public List<Libro> cargarLibros() throws IOException {
		return fdlibro.cargarLibros();
	}

	@Override
	public Libro verLibro(String id) {
		return fdlibro.verLibro(id);
	}

	@Override
	public void modificarLibro(Libro libro) throws IOException {
		fdlibro.ejecutaModificarLibro(libro);
		
	}

	@Override
	public List<Libro> eBuscarLibroTitulo(String tit) throws IOException {
		return fdlibro.ejecutaBuscarLibroTitulo(tit);
	}
	
	@Override
	public List<Libro> eBuscarLibroCategoria(String cat) throws IOException {
		return fdlibro.ejecutaBuscarLibroCat(cat);
	}
	
	

}
