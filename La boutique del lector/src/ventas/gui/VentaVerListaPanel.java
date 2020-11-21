package ventas.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import ventas.Venta;
import ventas.controller.VentasController;

/**
 * Clase que representa el panel en el que se muestran todas las ventas, o algunas ventas
 */
public class VentaVerListaPanel extends JPanel implements ActionListener, MouseListener{
	 static final long serialVersionUID = 1L;
	
	private VentasController _ctrl;
	private MyModelTableVen modelo;
	
	private JTable jt;
	private ButtonGroup grupo;
	private JRadioButton rb_id,rb_cliente, rb_sinFiltro;
	private JButton bt_buscar, bt_ver,bt_borrar;
	private JTextField texto;
	private String id;
	private String _rol;
	private String _dni;
	
	/**
	 * Constructora con parámetro
	 * @param dni 
	 * @param _rol 
	 * @param user_control AdminController
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public VentaVerListaPanel (VentasController ventas_control, String rol, String dni) throws IOException, NumberFormatException, ParseException {
		super();
		_ctrl = ventas_control;
		_rol = rol;
		_dni = dni;
		if(rol.equalsIgnoreCase("administrador")) {
			initVentaEditarPanel();
		}else {
			initVentaEditarPanelCliente();
		}
		
	}

	/**
	 * Método para inicializar los componentes de la ventana para un cliente.
	 */
	private void initVentaEditarPanelCliente() {
		setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Lista de ventas");
		setBorder(titulo);

		modelo = new MyModelTableVen();

		jt = new JTable(modelo);
		jt.addMouseListener(this);
		jt.setPreferredScrollableViewportSize(new Dimension(500,130));
		JScrollPane scrollPane = new JScrollPane(jt);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(2,1));
		
		JPanel panelBoton = new JPanel();
		panelBoton.setLayout(new FlowLayout());
		
		bt_ver = new JButton("ver");
		bt_ver.setName("ver");
		bt_ver.setEnabled(false);
		bt_ver.addActionListener(this);
		
		panelBoton.add(bt_ver);
		
		JPanel panelFiltro = new JPanel();
		panelFiltro.setLayout(new FlowLayout());
		
		JLabel filtro = new JLabel("Filtrar por:");
		grupo = new ButtonGroup();
		rb_id = new JRadioButton("ID",false);
		rb_sinFiltro = new JRadioButton("Sin filtro", true);
		rb_sinFiltro.addMouseListener(this);
		
		grupo.add(rb_id);
		grupo.add(rb_sinFiltro);
		
		
		panelFiltro.add(filtro);
		panelFiltro.add(rb_id);
		panelFiltro.add(rb_sinFiltro);
		
		jp1.add(panelBoton);
		jp1.add(panelFiltro);
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new FlowLayout());
		
		texto = new JTextField(10);
		
		bt_buscar = new JButton("Buscar");
		bt_buscar.setName("buscar");
		bt_buscar.addActionListener(this);
		
		jp2.add(texto);
		jp2.add(bt_buscar);
		
		add(scrollPane,BorderLayout.NORTH);
		add(jp1,BorderLayout.CENTER);
		add(jp2,BorderLayout.SOUTH);
		
		initVentasTable(_ctrl.buscarPorCliente(_dni));
	}

	/**
	 * Método para inicializar los componentes de la ventana para un administrador.
	 */
	private void initVentaEditarPanel(){
		setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Lista de ventas");
		setBorder(titulo);

		modelo = new MyModelTableVen();

		jt = new JTable(modelo);
		jt.addMouseListener(this);
		jt.setPreferredScrollableViewportSize(new Dimension(350,130));
		JScrollPane scrollPane = new JScrollPane(jt);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(2,1));
		
		JPanel panelBoton = new JPanel();
		panelBoton.setLayout(new FlowLayout());
		
		bt_ver = new JButton("ver");
		bt_ver.setName("ver");
		bt_ver.setEnabled(false);
		bt_ver.addActionListener(this);
		
		panelBoton.add(bt_ver);
		
		bt_borrar= new JButton("Borrar");
		bt_borrar.setName("Borrar");
		bt_borrar.setEnabled(false);
		bt_borrar.addActionListener(this);
		
		panelBoton.add(bt_borrar);
		
		JPanel panelFiltro = new JPanel();
		panelFiltro.setLayout(new FlowLayout());
		
		JLabel filtro = new JLabel("Filtrar por:");
		grupo = new ButtonGroup();
		rb_id = new JRadioButton("ID",false);
		rb_cliente=new JRadioButton("Cliente",false);
		rb_sinFiltro = new JRadioButton("Sin filtro", true);
		rb_sinFiltro.addMouseListener(this);
		
		grupo.add(rb_id);
		grupo.add(rb_cliente);
		grupo.add(rb_sinFiltro);
		
		
		panelFiltro.add(filtro);
		panelFiltro.add(rb_id);
		panelFiltro.add(rb_cliente);
		panelFiltro.add(rb_sinFiltro);
		
		jp1.add(panelBoton);
		jp1.add(panelFiltro);
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new FlowLayout());
		
		texto = new JTextField(10);
		
		bt_buscar = new JButton("Buscar");
		bt_buscar.setName("buscar");
		bt_buscar.addActionListener(this);
		
		jp2.add(texto);
		jp2.add(bt_buscar);
		
		add(scrollPane,BorderLayout.NORTH);
		add(jp1,BorderLayout.CENTER);
		add(jp2,BorderLayout.SOUTH);
		
		try {
			initVentasTable(_ctrl.cargarTodasLasVentas());
		} catch (IOException e) {
			showErrorMsg("Error en los archivos");
		}
	}
	
	/**
	 * Muestra un mensaje de información
	 * @param msg mensaje a mostrar
	 */
	public void showInfoMsg(String msg) {
		JOptionPane.showMessageDialog
		(this, msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
		
	}

	/**
	 * Muestra un mensaje de error
	 * @param msg mensaje a mostrar
	 */
	public void showErrorMsg(String msg) {
		JOptionPane.showMessageDialog
		(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == jt){
	        int row = jt.rowAtPoint(e.getPoint());
	        if (row >= 0 && jt.isEnabled())
	        {
	        	bt_ver.setEnabled(true);
	        	if(_rol.equalsIgnoreCase("administrador")) {
	        		bt_borrar.setEnabled(true);
	        	}
	        	id = modelo.getValueAt(row,0).toString();
	        }
		 }
	 else if(e.getSource() == rb_sinFiltro){
			 texto.setText("");
			 if(_rol.equalsIgnoreCase("administrador")) {
				 try {
					initVentasTable(_ctrl.cargarTodasLasVentas());
				} catch (Exception e1) {
					showErrorMsg("Error al cargar las ventas");
				}
			 }else {
				 initVentasTable(_ctrl.buscarPorCliente(_dni));
			 }
		 }
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton t = (JButton) e.getSource();
		String txt = t.getName();
		
		if(txt.equals("buscar")){
			if(rb_id.isSelected()){
				List<Venta> aux = new ArrayList<Venta>();
				Venta ax = _ctrl.verVenta(texto.getText().toUpperCase());
				if(ax != null) {
					aux.add(ax);
					if(_rol.equalsIgnoreCase("cliente")) {
						if(aux.get(0).getCliente().equalsIgnoreCase(_dni)) {
							initVentasTable(aux);
						}
						else {
							initVentasTable(aux);
						}
					}
					else {
						initVentasTable(aux);
					}
				}
				else {
					initVentasTable(aux);
				}
			} else if(_rol.equalsIgnoreCase("administrador")) { 
				if(rb_cliente.isSelected()){
					initVentasTable(_ctrl.buscarPorCliente(texto.getText()));
				}
			}
		}else if(txt.equals("Borrar")){
			int seleccionada = JOptionPane.showConfirmDialog
					(this,"¿Realmente desea eliminar esta venta?", "Eliminar",JOptionPane.YES_NO_OPTION);
			if(seleccionada == JOptionPane.YES_OPTION){
				try {
					_ctrl.eliminarVenta(id);
					try {
							initVentasTable(_ctrl.cargarTodasLasVentas());
						} catch (Exception e1) {
							showErrorMsg("Error al cargar las ventas");
					}
				} catch (IOException e1) {
					showErrorMsg("Error en los archivos.");
				}
			}
		}
		else if(txt.equals("ver")){
			verVenta(_ctrl.verVenta(id));
		}
	}

	

	/**
	 * Actualiza la tabla con la lista pasada por parámetro
	 * @param Lista de ventas lista de ventas a mostrar 
	 */
	public void initVentasTable(List<Venta> listaVentas) {
		modelo.initVentasTable(listaVentas);
	}

	/**
	 * Muestra la informacion de la venta seleccionada en una ventana
	 * @param v venta que se quiere mostrar
	 */
	public void verVenta(Venta v) {
		new VentanaVerVenta(v, _ctrl, _rol);
	}
}
