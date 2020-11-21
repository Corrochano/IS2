package views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import gui.GUI;
import libros.controller.LibrosController;
import libros.gui.LibroVerListaPanel;
import libros.gui.VentanaNuevoLibro;
import usuarios.controller.UsuariosController;
import usuarios.gui.ClienteAltaPanel;
import usuarios.gui.ClienteVerListaPanel;
import ventas.controller.VentasController;
import ventas.gui.VentaVerListaPanel;
import ventas.gui.VentanaNuevaVenta;

/**
 * Clase que extiende de GUI y crea la ventana del administrador.
 */
public class UsuarioGUI extends GUI implements ActionListener, MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	private UsuariosController user_control;
	private LibrosController lib_control;
	private VentasController ventas_control;
	private JPanel panel_actual;
	private JMenuBar jmb;
	private JMenu emp,lib ,log_out;
	private JMenuItem alta, lista, alta_libro, lista_libros,lista_ventas;
	private ClienteAltaPanel cli_alta_panel;
	private ClienteVerListaPanel cli_lista_panel;
	private LibroVerListaPanel lib_lista_panel;
	private VentanaNuevoLibro libro_alta_panel;
	private VentaVerListaPanel venta_lista;
	private VentanaNuevaVenta vent_nueva;
	private JMenu menuVentas;
	private JMenuItem hacerCompra, verMIsCompras;
	private JMenu log_out2;
	private JMenu ven;
	private JMenu librosCliente;
	private JMenuItem verlibroscli;
	
	private JLabel img_label;
	private ImageIcon imagen;
	
	private String _rol;
	private String _dni;
	
	/**
	 * Constructors con parámetro
	 * @param c 
	 * @param c AdminController
	 */
	public UsuarioGUI(String rol, String dni) {
		super(rol);
		user_control = new UsuariosController();
		lib_control=new LibrosController();
		ventas_control = new VentasController();
		if(rol.equalsIgnoreCase("administrador")) {
			iniGUIAdmin();
		}
		else if(rol.equalsIgnoreCase("cliente")) {
			iniGUICliente();
		}
		else {
			throw new IndexOutOfBoundsException();
		}
		_rol = rol;
		_dni = dni;
	}
	
	/**
	 * Método que nicializa los componentes de la ventana para un cliente.
	 */
	private void iniGUICliente() {
		panelPrincipal = this.getContentPane();
		panelPrincipal.setLayout(new FlowLayout());
		
		jmb = new JMenuBar();
		setJMenuBar(jmb);
		
		menuVentas = new JMenu("Compras");
		
		hacerCompra = new JMenuItem("Comprar un libro");
		hacerCompra.setName("comprar libro");
		hacerCompra.addActionListener(this);
		menuVentas.add(hacerCompra);
		
		verMIsCompras = new JMenuItem("Historial de compras");
		verMIsCompras.setName("historial compras");
		verMIsCompras.addActionListener(this);
		menuVentas.add(verMIsCompras);
		
		jmb.add(menuVentas);
		
		librosCliente = new JMenu("Libros");
		verlibroscli = new JMenuItem("Ver libros disponibles");
		verlibroscli.setName("verlibros");
		verlibroscli.addActionListener(this);
		
		librosCliente.add(verlibroscli);
		
		jmb.add(librosCliente);
		
		JSeparator sep = new JSeparator();
		
		jmb.add(sep);
		
		log_out2 = new JMenu("Salir");
		log_out2.setName("logout");
		log_out2.addMouseListener(this);	
		
		jmb.add(log_out2);
		
		panel_actual = new JPanel();
		
		imagen = new ImageIcon(getClass().getResource("FondoCliente.png"));
		img_label = new JLabel(imagen);
		img_label.setSize(5,5);
		panel_actual.add(img_label);
		
		panelPrincipal.add(panel_actual);
		
		
		setSize(600, 490);
		setVisible(true);
		setLocationRelativeTo(null);
		
	}

	/**
	 * Método que nicializa los componentes de la ventana para un administrador.
	 */
	private void iniGUIAdmin() {
		panelPrincipal = this.getContentPane();
		panelPrincipal.setLayout(new FlowLayout());
		
		jmb = new JMenuBar();
		setJMenuBar(jmb);
		
		emp = new JMenu("Clientes");
			
		alta = new JMenuItem("Dar de alta");
		alta.setName("alta");
		alta.addActionListener(this);
		
		lista = new JMenuItem("Ver lista");
		lista.setName("lista");
		lista.addActionListener(this);
		
		emp.add(alta);
		emp.add(lista);
		
		jmb.add(emp);
		
		lib= new JMenu("Libros");
		
		alta_libro=new JMenuItem("Dar de alta");
		alta_libro.setName("alta libro");
		alta_libro.addActionListener(this);
		
		lista_libros=new JMenuItem("Ver lista");
		lista_libros.setName("Editar libro");
		lista_libros.addActionListener(this);
		
		lib.add(alta_libro);
		lib.add(lista_libros);
		
		jmb.add(lib);
		
		ven = new JMenu("Ventas");
		
		lista_ventas=new JMenuItem("Ver ventas");
		lista_ventas.setName("ver ventas");
		lista_ventas.addActionListener(this);
		
		ven.add(lista_ventas);
		
		jmb.add(ven);
		
		JSeparator sep = new JSeparator();
		
		jmb.add(sep);
		
		log_out = new JMenu("Salir");
		log_out.setName("logout");
		log_out.addMouseListener(this);
		
		jmb.add(log_out);
		
		panel_actual = new JPanel();
		
		imagen = new ImageIcon(getClass().getResource("fondo.jpg"));
		img_label = new JLabel(imagen);
		img_label.setSize(5,5);
		panel_actual.add(img_label);
		
		panelPrincipal.add(panel_actual);
		
		
		setSize(600, 490);
		setVisible(true);
		setLocationRelativeTo(null);
		
	}
	
	/**
	 * Método para que se abra la ventana de la acción que desea realizar el cliente y se cambie a ella
	 * @param p JPanel
	 */
	public void changePanelActual(JPanel p){	
		panelPrincipal.remove(panel_actual);
		setVisible(false);
		panel_actual = p;
		panelPrincipal.add(panel_actual);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem j = (JMenuItem)e.getSource();
		String txt = j.getName();
		
		if(txt.equals("alta")){
			cli_alta_panel = new ClienteAltaPanel(user_control);
			changePanelActual(cli_alta_panel);	
		}
		else if(txt.equals("lista")){
			cli_lista_panel = new ClienteVerListaPanel(user_control);
			changePanelActual(cli_lista_panel);
		}
		else if(txt.equals("alta libro")){	
			libro_alta_panel = new VentanaNuevoLibro(lib_control);
			changePanelActual(libro_alta_panel);
		}else if(txt.equals("Editar libro")){	
			try {
				lib_lista_panel = new LibroVerListaPanel(lib_control, _rol);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			changePanelActual(lib_lista_panel);
		}else if(txt.equals("ver ventas")){	
			try {
				venta_lista = new VentaVerListaPanel(ventas_control, _rol, _dni);
			} catch (IOException e1) {
			
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				
				e1.printStackTrace();
			} catch (ParseException e1) {
				
				e1.printStackTrace();
			}
			changePanelActual(venta_lista);
		}
		else if(txt.equals("comprar libro")){
			vent_nueva = new VentanaNuevaVenta(ventas_control, _dni);
			changePanelActual(vent_nueva);
		}
		else if(txt.equals("verlibros")){
			try {
				lib_lista_panel = new LibroVerListaPanel(lib_control, _rol);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			changePanelActual(lib_lista_panel);
		}
		else if(txt.equals("historial compras")){
			try {
				venta_lista = new VentaVerListaPanel(ventas_control, _rol, _dni);
			} catch (IOException e1) {
			
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				
				e1.printStackTrace();
			} catch (ParseException e1) {
				
				e1.printStackTrace();
			}
			changePanelActual(venta_lista);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == log_out || e.getSource() == log_out2){
			int seleccionada = JOptionPane.showConfirmDialog
					(this,"¿Desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
			if(seleccionada == JOptionPane.YES_OPTION){
			    this.dispose();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}
