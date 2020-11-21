package ventas.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import gui.GUI;
import ventas.Venta;
import ventas.controller.VentasController;

/**
 * Clase que hereda de GUI, crea la ventana para ver una venta concreta siendo un administrador y permite editarla.
 */
public class VentanaVerVenta extends GUI implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private VentasController ventas_controller;
	private JPanel panel;
	private Venta venta;
	private JLabel labelId, labelPrecio, labelFecha, label_dni, label_libro;
	private JButton bt_editar,bt_guardar;
	private JTextField textId, textPrecio, textFecha, tf_libro, tf_dni;
	private String _rol;
	
	/**
	 * Constructora con parámetro.
	 * @param v Venta
	 * @param c AdminController
	 * @param rol 
	 */
	public VentanaVerVenta(Venta v, VentasController c, String rol) {
		super("Venta");
		ventas_controller = c;
		venta = v;
		_rol = rol;
		if(_rol.equalsIgnoreCase("administrador")) {
			initVentanaVerVenta();
		}else {
			initVentanaVerVentaCliente();
		}
	}
	
	/**
	 * Inicia la ventana con los componentes asociados a un cliente
	 */
	private void initVentanaVerVentaCliente() {
		panelPrincipal = this.getContentPane();
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Datos Venta");
		panel.setBorder(titulo);
		
		JPanel datos = new JPanel();
		datos.setLayout(new GridLayout(9,2));
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());
		
		labelId = new JLabel("ID:");
		textId = new JTextField(15);
		textId.setText(venta.getId());
		textId.setEditable(false);
		
		label_libro = new JLabel("Libro:");
		tf_libro = new JTextField(15);
		tf_libro.setText(venta.getLibro());
		tf_libro.setEditable(false);

		labelPrecio = new JLabel("Precio:");
		textPrecio = new JTextField(10);
		textPrecio.setText(String.valueOf(venta.getPrecio()));
		textPrecio.setEditable(false);
		
		labelFecha = new JLabel("Fecha:");
		Date date = venta.getDate();
		DateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy");
        String f = dateFormat.format(date);
		textFecha = new JTextField(10);
		textFecha.setText(f);
		textFecha.setEditable(false);
		
		datos.add(labelId);
		datos.add(textId);
		
		datos.add(labelFecha);
		datos.add(textFecha);
		
		datos.add(label_libro);
		datos.add(tf_libro);
		
		datos.add(labelPrecio);
		datos.add(textPrecio);
	
		panel.add(datos, BorderLayout.NORTH);
		panel.add(botones, BorderLayout.SOUTH);

		panelPrincipal.add(panel);
		
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}

	/**
	 * Método para inicializar los componentes de la ventana asociados a un administrador
	 */
	private void initVentanaVerVenta() {
		panelPrincipal = this.getContentPane();
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Datos Venta");
		panel.setBorder(titulo);
		
		JPanel datos = new JPanel();
		datos.setLayout(new GridLayout(9,2));
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());
		
		labelId = new JLabel("ID:");
		textId = new JTextField(15);
		textId.setText(venta.getId());
		textId.setEditable(false);
		
		label_dni = new JLabel("DNI:");
		tf_dni = new JTextField(15);
		tf_dni.setText(venta.getCliente());
		tf_dni.setEditable(false);
		
		label_libro = new JLabel("Libro:");
		tf_libro = new JTextField(15);
		tf_libro.setText(venta.getLibro());
		tf_libro.setEditable(false);

		labelPrecio = new JLabel("Precio:");
		textPrecio = new JTextField(10);
		textPrecio.setText(String.valueOf(venta.getPrecio()));
		textPrecio.setEditable(false);
		
		labelFecha = new JLabel("Fecha:");
		Date date = venta.getDate();
		DateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy");
        String f = dateFormat.format(date);
		textFecha = new JTextField(10);
		textFecha.setText(f);
		textFecha.setEditable(false);
		
		bt_editar=new JButton("editar");
		bt_editar.setName("editar");
		bt_editar.addActionListener(this);
		
		bt_guardar=new JButton("guardar");
		bt_guardar.setName("guardar");
		bt_guardar.setVisible(false);
		bt_guardar.addActionListener(this);
		
		botones.add(bt_editar);
		botones.add(bt_guardar);
		
		datos.add(labelId);
		datos.add(textId);
		
		datos.add(label_dni);
		datos.add(tf_dni);
		
		datos.add(labelFecha);
		datos.add(textFecha);
		
		datos.add(label_libro);
		datos.add(tf_libro);
		
		datos.add(labelPrecio);
		datos.add(textPrecio);
	
		panel.add(datos, BorderLayout.NORTH);
		panel.add(botones, BorderLayout.SOUTH);

		panelPrincipal.add(panel);
		
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		
	}

	/**
	 * Muestra un mensaje
	 * @param msg mensaje a mostrar
	 */
	public void showInfoMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
		
	}

	/**
	 * Muestra un error
	 * @param msg mensaje a mostrar
	 */
	public void showErrorMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton t = (JButton) e.getSource();
		String txt = t.getName();
		
		if(txt.equals("editar")){
			activarCampos();
			bt_guardar.setVisible(true);
		}
		else if(txt.equals("guardar")){
				String id = textId.getText();
				int pre = Integer.valueOf(textPrecio.getText());
				String dni = tf_dni.getText();
				String titulo = tf_libro.getText();
				String[] fechaTrozos = textFecha.getText().split("/");
				Date fecha = new Date();
				fecha.setDate(Integer.valueOf(fechaTrozos[0]));
				fecha.setMonth(Integer.valueOf(fechaTrozos[1]) - 1);
				fecha.setYear(Integer.valueOf(fechaTrozos[2]) - 1900);
				
				if(!id.isEmpty() && !dni.isEmpty()){
					int seleccionada = JOptionPane.showConfirmDialog
							(this,"¿Desea actualizar a esta venta?", "Actualizar",JOptionPane.YES_NO_OPTION);
					if(seleccionada == JOptionPane.YES_OPTION){
					  Venta v=new Venta(id, titulo, pre, dni, fecha);
					  try {
						ventas_controller.actualizarVenta(v);
					} catch (IOException e1) {
						showErrorMsg("Error en los archivos.");
					}
					  }
				}
				else{
					showInfoMsg("Se deben rellenar todos los campos.");	
				}
			
		}
	}
	
	/**
	 * Método para activar botones, textfield y combobox para editar.
	 */
	private void activarCampos(){
		bt_guardar.setVisible(true);
		tf_dni.setEditable(false);
		textId.setEditable(false);
		tf_libro.setEditable(false);
		textPrecio.setEditable(true);
		bt_editar.setVisible(false);
	}

}
