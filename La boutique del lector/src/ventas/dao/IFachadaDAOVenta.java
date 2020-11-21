package ventas.dao;

import java.io.IOException;
import java.util.List;

import libros.Libro;
import ventas.Venta;

/**
 * Interfaz de Clase que comunica el S.A. con el DAO de Ventas
 */
public interface IFachadaDAOVenta {
	
	/**
	 * Guarda una nueva venta en la BBDD si no existe una con su mismo ID.
	 * @param v la venta a guardar en la BBDD.
	 */
	public void ejecutaNuevaVenta(Venta v);
	
	/**
	 * Elimina una venta de la BBDD.
	 * @param id id de la venta a eliminar.
	 * @throws IOException Error en ventas.txt
	 */
	public void ejecutaEliminarVenta(String id) throws IOException;
	
	/**
	 * Actualiza una venta en la BBDD si existe.
	 * @param v la venta a actualizar.
	 * @throws IOException error en los archivos.
	 */
	public void ejecutaModificarVenta(Venta v) throws IOException;
	
	/**
	 * Devuelve una venta buscada si existe.
	 * @param id id de la venta a buscar.
	 * @return devuelve la venta buscada en una lista o la lista vacía si no existe la venta.
	 */
	public List<Venta> ejecutaConsultarVenta(String id);
	
	/**
	 * Filtra las ventas por cliente
	 * @param text id del cliente
	 * @return lista de las ventas de ese cliente
	 */
	public List<Venta> buscarPorCliente(String text);
	
	/**
	 * Busca un libro dado su id
	 * @param id id del libro
	 * @return libro buscado
	 */
	public Libro verLibro(String id);
	
	/**
	 * Elimina un libro de la bbdd
	 * @param id id del libro
	 */
	public void eliminarLibro(String id);
	
	/**
	 * Devuelve una lista de libros dada una categoria
	 * @param cat categria deseada
	 * @return lista de libros
	 * @throws IOException 
	 */
	public List<Libro> buscarLibroCat(String cat) throws IOException;
	
	/**
	 * Devuelve los libros de la bbdd dado su titulo
	 * @param tit titulo buscado
	 * @return lista de libros
	 * @throws IOException 
	 */
	public List<Libro> buscarLibroTit(String tit) throws IOException;
}
