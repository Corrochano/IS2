package ventas;

import java.util.Date;

/**
 * Clase que representa a una venta con sus atributos id, libro, precio, cliente, fechaVenta y pago (Si está pagada o no).
 */
public class Venta implements Comparable<Venta> {
	
	private String id;
	private String libro;
	private int precio;
	private String cliente;
	private Date fechaVenta;
	
	/**
	 * Constuctora con parámetros.
	 * @param identificador String
	 * @param librovendido Libro
	 * @param precioVenta int
	 * @param cliente Cliente
	 * @param fechaVenta Date
	 * @param pago VentaPagada enumerado que indica si está pagada o no
	 */
	public Venta(String identificador, String librovendido, int precioVenta, String cliente, Date fechaVenta) {
		this.id = identificador;
		this.setLibro(librovendido);
		this.precio = precioVenta;
		this.cliente = cliente;
		this.fechaVenta = fechaVenta;
	}
	
	/**
	 * Constructora con parámetro id
	 * @param _id String
	 */
	public Venta(String _id) {
		this.id = _id;
	}
	
	/**
	 * Getter para devolver el id de la venta
	 * @return id String
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Getter para devolver el precio de la venta
	 * @return precio int
	 */
	public int getPrecio() {
		return this.precio;
	}
	
	/**
	 * Getter para devolver el cliente de la venta
	 * @return cliente Cliente
	 */
	public String getCliente() {
		return this.cliente;
	}
	
	/**
	 * Getter para devolver la fecha de la venta
	 * @return fechaVenta Date
	 */
	public Date getDate() {
		return this.fechaVenta;
	}

	@Override
	public int compareTo(Venta o) {
		return this.id.compareToIgnoreCase(o.id);
	}
	
	/**
	 * Método para escribir la venta como un String
	 */
	@SuppressWarnings("deprecation")
	public String toString(){
		return id + " " + libro + " " + precio + " " + cliente + " " +
				fechaVenta.getDate() + " " + fechaVenta.getMonth() + " "  + fechaVenta.getYear();
	}

	/**
	 * Getter para devolver el libro de la venta
	 * @return libro Libro
	 */
	public String getLibro() {
		return libro;
	}

	/**
	 * Setter para asignar un libro a la venta
	 * @param librovendido Libro
	 */
	public void setLibro(String librovendido) {
		this.libro = librovendido;
	}
	
	
}

