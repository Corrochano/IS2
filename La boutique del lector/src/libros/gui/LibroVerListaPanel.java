package libros.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
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
import libros.controller.LibrosController;

/**
 * Clase que extiende a JPanel. Crea el panel de la lista de libros.
 */
@SuppressWarnings("serial")
public class LibroVerListaPanel extends JPanel implements ActionListener, MouseListener{

	private LibrosController libro_control;
	private MyModelTableLib modelo;
	private JTable jt;
	private JLabel filtro;
	private JTextField texto;
	private JRadioButton rb_categoria, rb_sinFiltro, rb_titulo;
	private ButtonGroup grupo;
	private JButton bt_buscar, bt_ver, bt_eliminar;
	private String id;
	private String _rol;
	
	/**
	 * Constructor con parámetro
	 * @param user_control
	 * @throws IOException 
	 */
	public LibroVerListaPanel(LibrosController lib_control, String rol) throws IOException{
		super();
		libro_control = lib_control;
		_rol = rol;
		
		switch(_rol) {
		case "administrador":
			initListaLibrosAdmin();
			break;
		case "cliente":
			initListaLibrosCliente();
			break;
		}	
	}
	
	/**
	 * Init de los componentes de la ventana para clientes.
	 * @throws IOException Si hay error en la BBDD.
	 */
	private void initListaLibrosCliente() throws IOException {
		setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Lista de libros");
		setBorder(titulo);
		
		modelo = new MyModelTableLib();
		
		jt = new JTable(modelo);
		jt.addMouseListener(this);
		jt.setPreferredScrollableViewportSize(new Dimension(500,130));
		JScrollPane scrollPane = new JScrollPane(jt);
		libro_control.cargarTodosLosLibros();
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(2,1));
		
		JPanel panelboton = new JPanel();
		panelboton.setLayout(new FlowLayout());
		
		bt_ver = new JButton("ver");
		bt_ver.setName("ver");
		bt_ver.setEnabled(false);
		bt_ver.addActionListener(this);
		
		panelboton.add(bt_ver);
		
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
				initLibrosTable(libro_control.cargarTodosLosLibros());
		} catch (IOException e1) {
				showErrorMsg("Error al cargar los libros.");
			}
    }

	/**
	 * Init de los componentes de la ventana para administradores.
	 * @throws IOException Si hay error en la BBDD.
	 */
	private void initListaLibrosAdmin() throws IOException{
        setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Lista de libros");
		setBorder(titulo);
		
		modelo = new MyModelTableLib();
		
		jt = new JTable(modelo);
		jt.addMouseListener(this);
		jt.setPreferredScrollableViewportSize(new Dimension(500,130));
		JScrollPane scrollPane = new JScrollPane(jt);
		libro_control.cargarTodosLosLibros();
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(2,1));
		
		JPanel panelboton = new JPanel();
		panelboton.setLayout(new FlowLayout());
		
		bt_ver = new JButton("ver");
		bt_ver.setName("ver");
		bt_ver.setEnabled(false);
		bt_ver.addActionListener(this);
		
		
		bt_eliminar = new JButton("eliminar");
		bt_eliminar.setName("eliminar");
		bt_eliminar.setEnabled(false);
		bt_eliminar.addActionListener(this);
		
		panelboton.add(bt_ver);
		panelboton.add(bt_eliminar);
		
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
				initLibrosTable(libro_control.cargarTodosLosLibros());
		} catch (IOException e1) {
				showErrorMsg("Error al cargar los libros.");
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
		new VentanaVerLibro(lib, libro_control, _rol);
	}

	@Override
	public void mouseClicked(MouseEvent e) {		
		 if(e.getSource() == jt){
		        int row = jt.rowAtPoint(e.getPoint());
		        if (row >= 0 && jt.isEnabled())
		        {
		        	bt_ver.setEnabled(true);
		        	if(_rol.equalsIgnoreCase("administrador")) {
		        		bt_eliminar.setEnabled(true);
		        	}
		        	id = modelo.getValueAt(row,0).toString();
		        }
			 }
		 else if(e.getSource() == rb_sinFiltro){
			 texto.setText("");
			 try {
				 initLibrosTable(libro_control.cargarTodosLosLibros());
			} catch (IOException e1) {
				showErrorMsg("Error al cargar los libros.");
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
					initLibrosTable(libro_control.buscarLibroCat(texto.getText()));
				
				}
				catch(IOException e1) {
					showErrorMsg("No se ha encontrado libros.txt.");
				}
			}
			if(rb_titulo.isSelected()) {
				try {
					initLibrosTable(libro_control.buscarLibroTit(texto.getText()));
				} catch (IOException e1) {
					showErrorMsg("No se ha encontrado libros.txt.");
				}
			}
		}
		else if(txt.equals("ver")){
			verLibro(libro_control.verLibro(id));
			try {
				initLibrosTable(libro_control.cargarTodosLosLibros());
			}catch(IOException e2){
				showErrorMsg("Error en los archivos");
			}
		}
		else if(txt.equals("eliminar")){
			int seleccionada = JOptionPane.showConfirmDialog
					(this,"Realmente desea eliminar este libro?", "Eliminar",JOptionPane.YES_NO_OPTION);
			if(seleccionada == JOptionPane.YES_OPTION){
				try {
					libro_control.eliminarLibroButton(id);
				} catch (IOException e1) {
					showErrorMsg("Error al cargar en la BBDD.");
				}finally {
					try {
						modelo.initLibrosTable(libro_control.cargarTodosLosLibros());
					} catch (IOException e1) {
						showErrorMsg("Error al cargar en la BBDD.");
					}
				}	
			}
		}
	}
}
