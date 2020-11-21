package ventas.fachada;

import java.io.IOException;
import java.util.List;

import libros.Libro;
import ventas.Venta;
import ventas.sa.SAVenta;

/**
 * Clase que representa la fachada de una venta
 */
public class VentasFachada {

private SAVenta sa_venta;
	
	/**
	 * Constructora sin parámetros
	 */
	public VentasFachada() {
	 sa_venta = new SAVenta();
	}

	/**
	 * Carga todas las ventas de la bbdd en una lista
	 * @return lista lista de ventas de la bbdd
	 * @throws IOException error en los archivos
	 */
	public List<Venta> cargarTodasLasVentas() throws IOException{
		return sa_venta.cargarVentas();
	}

	/**
	 * Da de alta una nueva venta en la bbdd
	 * @param v venta asociada
	 */
	public void altaVenta(Venta v) {
		sa_venta.nuevaVenta(v);
	}

	/**
	 * Carga todos los libros de la bbdd en una lista
	 * @return lista lista de libros de la bbdd
	 * @throws IOException error en los archivos
	 */
	public List<Libro> cargarTodosLosLibros() throws NumberFormatException, IOException {
		return sa_venta.cargarLibros();
	}

	/**
	 * Actualiza una venta
	 * @param v venta a actualizar
	 * @throws IOException Error en los archivos.
	 */
	public void actualizarVenta(Venta v) throws IOException {
		sa_venta.modificarVenta(v);
	}

	/**
	 * Da la informacion de una venta.
	 * @param id id de la venta a ver.
	 */
	public Venta verVenta(String id) {
		List<Venta> aux = sa_venta.consultarVenta(id);
		if(aux == null) return null;
		return aux.get(0);
	}

	/**
	 * Devuelve una lista de libros dada una categoria
	 * @param cat categria deseada
	 * @return lista de libros
	 * @throws IOException 
	 */
	public List<Libro> buscarLibroCat(String cat) throws IOException {
		return sa_venta.buscarLibroCat(cat);
	}

	/**
	 * Filtra las ventas por cliente
	 * @param text id del cliente
	 * @return lista de las ventas de ese cliente
	 */
	public List<Venta> buscarPorCliente(String text) {
		return sa_venta.buscarPorCliente(text);
	}
	/**
	 * Borra una venta de la bbdd
	 * @param id id de la venta a eliminar
	 * @throws IOException Error en los archivos.
	 */
	public void eliminarVenta(String id) throws IOException {
		sa_venta.eliminarVenta(id);
	}

	/**
	 * Dado un id devuelve toda la informacion del libro asociado a ese id
	 * @param id id del libro
	 * @return libro libro asociado
	 */
	public Libro verLibro(String id) {
		return sa_venta.verLibro(id);
	}
	/**
	 * Elimina un libro de la bbdd
	 * @param id id del libro
	 */
	public void eliminarLibro(String id) {
		sa_venta.eliminarLibro(id);
	}

	/**
	 * Devuelve los libros de la bbdd dado su titulo
	 * @param tit titulo buscado
	 * @return lista de libros
	 * @throws IOException 
	 */
	public List<Libro> buscarLibroTit(String tit) throws IOException {
		return sa_venta.buscarLibroTit(tit);
	}

}
