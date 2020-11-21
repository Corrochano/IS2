package ventas.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import gui.GUI;
import libros.CategoriaEnum;
import libros.Libro;

/**
 * Clase que muestra los datos de un libro en el subsistema de ventas
 */
public class VentanaVerLibroVenta extends GUI{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private Libro libro;
	private JLabel lb_id, lb_titulo, lb_autor, lb_precio_alquiler, lb_categoria;
	private JTextField tf_id, tf_titulo, tf_precio_alquiler, tf_autor;
	private JComboBox<CategoriaEnum> cb_categoria;
	
	
	/**
	 * Constructora con parametros
	 * @param l
	 * @param ventas_control
	 */
	public VentanaVerLibroVenta(Libro l) {
		super("Libro");
		libro = l;
		initVentanaVerLibro();
	}
	
	/**
	 * Inicia los componentes de la ventana
	 */
	private void initVentanaVerLibro() {
		panelPrincipal = this.getContentPane();
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Datos Libro");
		panel.setBorder(titulo);
		
		JPanel datos = new JPanel();
		datos.setLayout(new GridLayout(9,2));
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());
		
		lb_id = new JLabel("Id: ");
		tf_id = new JTextField(15);
		tf_id.setText(libro.get_id());
		tf_id.setEditable(false);
		
		lb_titulo = new JLabel("Titulo: ");
		tf_titulo = new JTextField(10);
		tf_titulo.setText(libro.get_titulo());
		tf_titulo.setEditable(false);
		
		lb_precio_alquiler = new JLabel("Precio: ");
		tf_precio_alquiler = new JTextField(10);
		tf_precio_alquiler.setText(String.valueOf(libro.get_precio()));
		tf_precio_alquiler.setEditable(false);
		
		lb_autor = new JLabel("Autor: ");
		tf_autor = new JTextField(10);
		tf_autor.setText(String.valueOf(libro.get_autor()));
		tf_autor.setEditable(false);
		
		lb_categoria = new JLabel("Categoria");
		cb_categoria = new JComboBox<CategoriaEnum>();
		cb_categoria.setBounds(10, 10, 80, 20);
		cb_categoria.addItem(CategoriaEnum.ARTE);
		cb_categoria.addItem(CategoriaEnum.CIENCIA);
		cb_categoria.addItem(CategoriaEnum.HISTORIA);
		cb_categoria.addItem(CategoriaEnum.INFANTIL);
		cb_categoria.addItem(CategoriaEnum.TERROR);
		cb_categoria.setSelectedItem(libro.get_categoria());
		cb_categoria.setEnabled(false);

		datos.add(lb_id);
		datos.add(tf_id);
		
		datos.add(lb_titulo);
		datos.add(tf_titulo);
		
		datos.add(lb_precio_alquiler);
		datos.add(tf_precio_alquiler);
		
		datos.add(lb_autor);
		datos.add(tf_autor);
		
		datos.add(lb_categoria);
		datos.add(cb_categoria);

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
		(this,msg, "Error",JOptionPane.ERROR_MESSAGE);
	}
}
