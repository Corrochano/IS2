package ventas.sa;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import libros.Libro;
import ventas.Venta;

/**
 * Clase que representa la interfaz de un servicio de aplicacion de una venta
 */
public interface ISAVenta {

	/**
	 * LLama a la DAO para que guarde la venta en la BBDD
	 * @param v la venta a añadir
	 */
	public void nuevaVenta(Venta v);
	
	/**
	 * Llama al DAO para que elimine de la BBDD la venta con el siguiente id.
	 * @param id el id de la venta a eliminar
	 * @throws IOException Error en los archivos.
	 */
	public void eliminarVenta(String id) throws IOException;
	
	/**
	 * Actualiza la informacion de una venta (Si existia en la bbdd).
	 * @param v venta que quieres actualizar.
	 * @throws IOException Error en los archivos.
	 */
	public void modificarVenta(Venta v) throws IOException;
	
	/**
	 * LLama al DAO para que, dado un id devuelva la venta con ese id dentro de una lista (si existe en la bdd)
	 * @param id ide de la venta a consultar.
	 * @return
	 */
	public List<Venta> consultarVenta(String id);
	/**
	 * Carga todas las ventas de la bbdd
	 * @return listVenta ventas encontradas
	 * @throws IOException error al cargar ventas
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public List<Venta> cargarVentas() throws IOException, NumberFormatException, ParseException;

	/**
	 * Filtra la lista de ventas por cliente
	 * @param text id del cliente buscado
	 * @return lista lista de ventas del cliente
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
	public void eliminarLibro(String id) ;
	
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
