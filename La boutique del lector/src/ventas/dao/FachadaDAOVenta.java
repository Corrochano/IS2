package ventas.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import libros.Libro;
import ventas.Venta;

/**
 * Clase que comunica la SA con el DAO de venta.
 */
public class FachadaDAOVenta implements IFachadaDAOVenta {
	private DAOVenta daoV;
	
	/**
	 * Constructora con parámetro.
	 * @param dV dao de venta.
	 */
	public FachadaDAOVenta(DAOVenta dV) {
		daoV = dV;
	}

	@Override
	public void ejecutaNuevaVenta(Venta v) {
		daoV.eNuevaVenta(v);
		
	}

	@Override
	public void ejecutaEliminarVenta(String id) throws IOException {
		daoV.eEliminarVenta(id);
	}

	@Override
	public void ejecutaModificarVenta(Venta v) throws IOException {
		daoV.eModificarVenta(v);
	}

	@Override
	public List<Venta> ejecutaConsultarVenta(String id) {
		return daoV.eConsultarVenta(id);
	}

	/**
	 * Cargas las ventas de la bbdd en una lista
	 * @return lista con todas las ventas de la bbdd
	 * @throws IOException error en los archivos
	 */
	public List<Venta> ejecutarCargarVentas() throws IOException{
		FileInputStream ventas = null;
		ventas = new FileInputStream("ficheros/ventas.txt");
		return daoV.cargarVentas(ventas);
	}

	/**
	 * Carga todos los libros de la bbdd en una lista
	 * @return lista de libros de la bbdd
	 * @throws NumberFormatException error con los parametros
	 * @throws IOException error en los archivos
	 */
	public List<Libro> ejecutarCargarLibros() throws NumberFormatException, IOException {
		FileInputStream libros = null;
		libros = new FileInputStream("ficheros/libros.txt");
		return daoV.cargarLibros(libros);
	}

	@Override
	public List<Venta> buscarPorCliente(String text) {
		return daoV.eBuscarVentaCliente(text);
	}

	@Override
	public Libro verLibro(String id) {
		return daoV.eConsultarLibro(id).get(0);
	}

	@Override
	public void eliminarLibro(String id) {
		daoV.eliminarLibro(id);
	}

	@Override
	public List<Libro> buscarLibroCat(String cat) throws IOException {
		return daoV.buscarLibroCat(cat);
	}

	@Override
	public List<Libro> buscarLibroTit(String tit) throws IOException {
		return daoV.buscarLibroTit(tit);
	}

}
