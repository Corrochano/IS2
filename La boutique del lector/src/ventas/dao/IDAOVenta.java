package ventas.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import libros.Libro;
import ventas.Venta;

/**
 * Clase que representa la interfaz de una dao de venta
 */
public interface IDAOVenta {

	/**
	 * Guarda una nueva venta en la BBDD si no existe ya una con ese id.
	 * @param v venta que se debe añadir a la BBDD.
	 */
	public void eNuevaVenta(Venta v);
	
	/**
	 * Elimina la venta con el id del parámetro de la BBDD (Si existe).
	 * @param id id de la venta a eliminar.
	 * @throws IOException Error al cargar en ventas.txt
	 */
	public void eEliminarVenta(String id) throws IOException;
	
	/**
	 * Mira si una venta existe ya en la BBDD.
	 * @param id id de la venta a buscar.
	 * @return si existe o no la venta.
	 */
	public boolean eBuscarVenta(String id);
	
	/**
	 * Actualiza una venta en la BBDD (si existe).
	 * @param v la venta a actualizar
	 * @throws IOException Error en ventas.txt
	 */
	public void eModificarVenta(Venta v) throws IOException;
	
	/**
	 * Devuelve la venta con el id del parámetro si existe.
	 * @param id id de la venta a buscar.
	 * @return la venta buscada dentro de una lista o la lista vacía si no existe dixha venta.
	 */
	public List<Venta> eConsultarVenta(String id);
	
	/**
	 * Carga en una lista de libros todos los libros de la bbdd
	 * @param libros input stream
	 * @return lista lista de libros de la bbdd
	 * @throws IOException error en los archivos
	 */
	public List<Libro> cargarLibros(FileInputStream libros) throws IOException;
	
	/**
	 * Filtra las ventas por cliente
	 * @param text id del cliente
	 * @return lista de las ventas de ese cliente
	 */
	public List<Venta> eBuscarVentaCliente(String text);
	
	/**
	 * Busca un libro en la bbdd dado su id
	 * @param id id del libro
	 * @return libro buscado
	 */
	public List<Libro> eConsultarLibro(String id);
	
	/**
	 * Elimina un libro de la bbdd
	 * @param id id del libro
	 */
	public void eliminarLibro(String id);
	
	/**
	 * Devuelve una lista de libros dada una categoria
	 * @param cat categria deseada
	 * @return lista de libros
	 */
	public List<Libro> buscarLibroCat(String cat) throws IOException;
	
	/**
	 * Devuelve una lista de libros dado su titulo
	 * @param tit titulo buscado
	 * @return lista de libros
	 */
	public List<Libro> buscarLibroTit(String tit) throws IOException;
}
