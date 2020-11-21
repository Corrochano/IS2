package ventas.sa;

import java.io.IOException;
import java.util.List;

import libros.Libro;
import ventas.Venta;
import ventas.dao.DAOVenta;
import ventas.dao.FachadaDAOVenta;

public class SAVenta implements ISAVenta{
	
	FachadaDAOVenta facDVenta;
	DAOVenta daoVenta;
	
	/**
	 * Constructora sin parámetros.
	 */
	public SAVenta() {
		daoVenta = new DAOVenta();
		facDVenta = new FachadaDAOVenta(daoVenta);
	}

	@Override
	public void nuevaVenta(Venta v) {
		facDVenta.ejecutaNuevaVenta(v);
	}

	@Override
	public void eliminarVenta(String id) throws IOException {
		facDVenta.ejecutaEliminarVenta(id);
	}

	@Override
	public void modificarVenta(Venta v) throws IOException {
		facDVenta.ejecutaModificarVenta(v);
	}

	@Override
	public List<Venta> consultarVenta(String id) {
		return facDVenta.ejecutaConsultarVenta(id);
	}

	@Override
	public List<Venta> cargarVentas() throws IOException{
		return facDVenta.ejecutarCargarVentas();
	}

	/**
	 * Carga todos los libros de una bbdd en una lista
	 * @return lista lista de libros de la bbdd
	 * @throws NumberFormatException error con parametros
	 * @throws IOException error en los archivos
	 */
	public List<Libro> cargarLibros() throws NumberFormatException, IOException {
		return facDVenta.ejecutarCargarLibros();
	}

	@Override
	public List<Venta> buscarPorCliente(String text) {
		return facDVenta.buscarPorCliente(text);
	}

	@Override
	public Libro verLibro(String id) {
		return facDVenta.verLibro(id);
	}

	@Override
	public void eliminarLibro(String id) {
		facDVenta.eliminarLibro(id);
	}

	@Override
	public List<Libro> buscarLibroCat(String cat) throws IOException {
		return facDVenta.buscarLibroCat(cat);
	}

	@Override
	public List<Libro> buscarLibroTit(String tit) throws IOException {
		return facDVenta.buscarLibroTit(tit);
	}
	
}
