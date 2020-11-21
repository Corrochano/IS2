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
import java.util.Calendar;
import java.util.Date;
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

import libros.CategoriaEnum;
import libros.Libro;
import libros.gui.MyModelTableLib;
import ventas.Venta;
import ventas.controller.VentasController;

/**
 * Clase que extiende de JPanel y que implemente ActionListener y ItemListener, crea la ventana para añadir una nueva venta.
 */
public class VentanaNuevaVenta extends JPanel implements ActionListener,MouseListener{
	
	private static final long serialVersionUID = 1L;
	
	private VentasController ventas_control;
	private MyModelTableLib modelo;
	private JTable jt;
	private JLabel filtro;
	private JTextField texto;
	private JRadioButton rb_categoria, rb_sinFiltro, rb_titulo;
	private ButtonGroup grupo;
	private JButton bt_buscar, bt_ver, bt_comprar;
	private String id;
	private String dni;
	
	/**
	 * Constructora con parámetro
	 * @param user_control ClienteController
	 */
	public VentanaNuevaVenta (VentasController v_control, String _dni) {
		this.ventas_control = v_control;
		dni = _dni;
		initVentanaNuevaVenta();
	}
	
	/**
	 * Método para inicializar los componentes de la ventana.
	 */
	private void initVentanaNuevaVenta() {
		setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Lista de libros");
		setBorder(titulo);
		
		modelo = new MyModelTableLib();
		
		jt = new JTable(modelo);
		jt.addMouseListener(this);
		jt.setPreferredScrollableViewportSize(new Dimension(350,130));
		JScrollPane scrollPane = new JScrollPane(jt);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(2,1));
		
		JPanel panelboton = new JPanel();
		panelboton.setLayout(new FlowLayout());
		
		bt_ver = new JButton("Ver");
		bt_ver.setName("ver");
		bt_ver.setEnabled(false);
		bt_ver.addActionListener(this);
		
		panelboton.add(bt_ver);
		
		bt_comprar = new JButton("Comprar");
		bt_comprar.setName("comprar");
		bt_comprar.setEnabled(false);
		bt_comprar.addActionListener(this);
		
		panelboton.add(bt_comprar);
		
		JPanel panelfiltro = new JPanel();
		panelfiltro.setLayout(new FlowLayout());
		
		filtro = new JLabel("Filtrar por:");
		grupo = new ButtonGroup();
		rb_categoria = new JRadioButton("Categoria",false);
		rb_sinFiltro = new JRadioButton("Sin flitro",true);
		rb_titulo = new JRadioButton("Título", false);
		rb_sinFiltro.addMouseListener(this);
		
		grupo.add(rb_categoria);
		grupo.add(rb_titulo);
		grupo.add(rb_sinFiltro);
		
		panelfiltro.add(filtro);
		panelfiltro.add(rb_categoria);
		panelfiltro.add(rb_titulo);
		panelfiltro.add(rb_sinFiltro);
		
		jp1.add(panelboton);
		jp1.add(panelfiltro);
		
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
			initLibrosTable(ventas_control.cargarTodosLosLibros());
		} catch (IOException e) {
			showErrorMsg("Error en los archivos");
		}
    }
	
	/**
	 * Actualiza la tabla de libros.
	 */
	public void initLibrosTable( List<Libro> lista_libros) {
		modelo.initLibrosTable(lista_libros);
	}
	
	/**
	 * Init del combo de categorias
	 */
	public void initCategoriasCombo(List<CategoriaEnum> _categorias) {
		modelo.initCategoriasCombo(_categorias);
	}
	
	/**
	 * Mensaje de informacion
	 */
	public void showInfoMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Aviso" ,JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Mensaje de error
	 */
	public void showErrorMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Error",JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Muestra la informacion de un libro
	 */
	public void verLibro(Libro lib) {
		new VentanaVerLibroVenta(lib);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {		
		 if(e.getSource() == jt){
		        int row = jt.rowAtPoint(e.getPoint());
		        if (row >= 0 && jt.isEnabled())
		        {
		        	bt_ver.setEnabled(true);
		        	bt_comprar.setEnabled(true);
		        	id = modelo.getValueAt(row,0).toString();
		        }
			 }
		 else if(e.getSource() == rb_sinFiltro){
			 texto.setText("");
			 try {
				initLibrosTable(ventas_control.cargarTodosLosLibros());
			} catch (IOException e1) {
				showErrorMsg("Error en los archivos");
			}
		 }
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton t = (JButton) e.getSource();
		String txt = t.getName();
		
		if(txt.equals("buscar")){
			if(rb_categoria.isSelected()){
				
				try {
					initLibrosTable(ventas_control.buscarLibroCat(texto.getText()));
				} catch (IOException e1) {
					showErrorMsg("Error en los archivos");
				}
			}
			if(rb_titulo.isSelected()) {
				try {
					initLibrosTable(ventas_control.buscarLibroTit(texto.getText()));
				} catch (IOException e1) {
					showErrorMsg("Error en los archivos");
				}
			}
		}
		else if(txt.equals("ver")){
			verLibro(ventas_control.verLibro(id));
			try {
				initLibrosTable(ventas_control.cargarTodosLosLibros());
			}catch(IOException e2){
				showErrorMsg("Error en los archivos");
			}
		}
		else if(txt.equals("comprar")){
			int seleccionada = JOptionPane.showConfirmDialog
					(this,"Realmente desea comprar este libro?", "Comprar",JOptionPane.YES_NO_OPTION);
			if(seleccionada == JOptionPane.YES_OPTION){
				try {
					List<Venta> aux = ventas_control.cargarTodasLasVentas();
					String ventaid;
					int max =0;
					for(Venta v : aux) {
						ventaid = v.getId();
						ventaid = ventaid.substring(1);
						if(max < Integer.parseInt(ventaid)) {
							max = Integer.parseInt(ventaid);
						}
					}
					max++;
					String _ventaid = "V" + max;
					int precio = ventas_control.verLibro(id).get_precio();
					Date today = Calendar.getInstance().getTime();
					Venta v = new Venta(_ventaid, ventas_control.verLibro(id).get_titulo().replace(' ', '_'), precio, dni, today);
					ventas_control.altaVenta(v);
					ventas_control.eliminarLibro(id);
				} catch (IOException e1) {
					showErrorMsg("Error al cargar en la BBDD.");
				} catch (NumberFormatException e1) {
					showInfoMsg("No puede tener numeros negativos en precio"); 
				}finally {
					try {
						modelo.initLibrosTable(ventas_control.cargarTodosLosLibros());
					} catch (IOException e1) {
						showErrorMsg("Error en los archivos");
					}
				}	
			}
		}
	}
	
}
