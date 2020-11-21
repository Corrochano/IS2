package ventas.controller;

import java.io.IOException;
import java.util.List;

import libros.Libro;
import ventas.Venta;
import ventas.fachada.VentasFachada;

/**
 * Clase que representa el controlador de una venta
 */
public class VentasController {
	
	private VentasFachada ventaFachada=new VentasFachada();
	
	/**
	 * Constructora vacía
	 */
	public VentasController() {}
	
	/**
	 * Actualiza una venta
	 * @param v venta a actualizar
	 * @throws IOException Error en los archivos.
	 */
	public void actualizarVenta(Venta v) throws IOException{
		ventaFachada.actualizarVenta(v);
	}

	/**
	 * Devuelve todas las ventas en la tabla.
	 * @return las ventas que hay en la BBDD.
	 * @throws IOException
	 */
	public List<Venta> cargarTodasLasVentas() throws IOException{
		return ventaFachada.cargarTodasLasVentas();
	}
	

	/**
	 * Da la informacion de una venta.
	 * @param id id de la venta a ver.
	 */
	public Venta verVenta(String id) {
		return ventaFachada.verVenta(id);
	}
	
	/**
	 * Da de alta la venta pasada por parámetro
	 * @param v venta a dar de alta
	 */
	public void altaVenta(Venta v) throws IOException{
		ventaFachada.altaVenta(v);
	}

	/**
	 * Carga todos los libros de la bbdd en una lista
	 * @return lista lista de libros de la bbdd
	 * @throws IOException error en los archivos
	 */
	public List<Libro> cargarTodosLosLibros() throws IOException {
		return ventaFachada.cargarTodosLosLibros();
	}

	/**
	 * Devuelve los libros de la bbdd dado su titulo
	 * @param tit titulo buscado
	 * @return lista de libros
	 * @throws IOException 
	 */
	public List<Libro> buscarLibroTit(String tit) throws IOException {
		return ventaFachada.buscarLibroTit(tit);
	}

	/**
	 * Busca todos los libros de la bbdd pertenecientes a una categoria
	 * @param cat categoria buscada
	 * @return lista lista de libros pertenecientes a esa categoria
	 * @throws IOException erroe en los archivos
	 */
	public List<Libro> buscarLibroCat(String cat) throws IOException {
		return ventaFachada.buscarLibroCat(cat);
	}

	/**
	 * Dado un id devuelve toda la informacion del libro asociado a ese id
	 * @param id id del libro
	 * @return libro libro asociado
	 */
	public Libro verLibro(String id) {
		return ventaFachada.verLibro(id);
	}

	/**
	 * Filtra las ventas por cliente
	 * @param text id del cliente
	 * @return lista de las ventas de ese cliente
	 */
	public List<Venta> buscarPorCliente(String text) {
		return ventaFachada.buscarPorCliente(text);
	}

	/**
	 * Borra una venta de la bbdd
	 * @param id id de la venta a eliminar
	 * @throws IOException Error en los archivos.
	 */
	public void eliminarVenta(String id) throws IOException {
		ventaFachada.eliminarVenta(id);
	}

	/**
	 * Elimina un libro de la bbdd
	 * @param id id del libro
	 */
	public void eliminarLibro(String id) {
		ventaFachada.eliminarLibro(id);
	}

}
