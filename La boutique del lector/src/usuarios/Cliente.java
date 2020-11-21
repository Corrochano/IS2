package usuarios;

import java.util.ArrayList;
import java.util.List;

import ventas.Venta;

/**
 * Clase que extiende de Persona y que representa a un cliente
 */
public class Cliente extends Persona{
	private List<Venta> ventas = new ArrayList<Venta>();
	
	/**
	 * Constructora con par�metros
	 * @param n String Nombre del cliente
	 * @param a String Apellido del cliente
	 * @param d String DNI del cliente
	 * @param m String Email del cliente
	 */
	public Cliente(String n, String a, String d, String m) {
		super(n, a, d, m);
	}

	/**
	 * A�ade una venta a la lista de ventas y actualiza su paga al del par�metro pagado
	 * @param venta Venta
	 * @param pagado String indica si est� o no pagada la venta
	 */
	public void addVenta(Venta venta) {
		ventas.add(venta);
	}
	
}

