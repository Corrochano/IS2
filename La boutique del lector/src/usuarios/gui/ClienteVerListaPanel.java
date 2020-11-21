package usuarios.gui;

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

import usuarios.Cliente;
import usuarios.Usuario;
import usuarios.controller.UsuariosController;



/**
 * Clase que extiende a JPanel. Crea el panel de la lista de clientes,
 * implementa ActionListener, MouseListener, ClientesObserver
 */
@SuppressWarnings("serial")
public class ClienteVerListaPanel  extends JPanel implements ActionListener, MouseListener{

	private UsuariosController user_control;
	private MyModelTableCli modelo;
	private JTable jt;
	private JLabel filtro;
	private JTextField texto;
	private JRadioButton rb_dni, rb_sinFiltro;
	private ButtonGroup grupo;
	private JButton bt_buscar, bt_ver, bt_eliminar;
	private String dni;
	
	/**
	 * Constructor con parámetro
	 * @param user_control un admincontroller
	 */
	public ClienteVerListaPanel(UsuariosController u){
		super();
		user_control = u;
		initCliEditarPanel();
	}

	/**
	 * Inicializa todos los componentes
	 */
    private void initCliEditarPanel(){
        setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Ver lista de clientes");
		setBorder(titulo);
		
		modelo = new MyModelTableCli();
		try {
			initClientesTable(user_control.cargarClientes());
			modelo.fireTableDataChanged();
		}catch(IOException e){
			showErrorMsg("Error al cargar en los archivos");
		}
		jt = new JTable(modelo);
		jt.addMouseListener(this);
		jt.setPreferredScrollableViewportSize(new Dimension(500,130));
		JScrollPane scrollPane = new JScrollPane(jt);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(2,1));
		
		JPanel panelboton = new JPanel();
		panelboton.setLayout(new FlowLayout());
		
		bt_ver = new JButton("VER");
		bt_ver.setName("ver");
		bt_ver.setEnabled(false);
		bt_ver.addActionListener(this);
		
		bt_eliminar = new JButton("ELIMINAR");
		bt_eliminar.setName("eliminar");
		bt_eliminar.setEnabled(false);
		bt_eliminar.addActionListener(this);
		
		panelboton.add(bt_ver);
		panelboton.add(bt_eliminar);
		
		JPanel panelfiltro = new JPanel();
		panelfiltro.setLayout(new FlowLayout());
		
		filtro = new JLabel("Filtrar por:");
		grupo = new ButtonGroup();
		rb_dni = new JRadioButton("DNI",false);
		rb_sinFiltro = new JRadioButton("SIN FILTROS");
		rb_sinFiltro.addMouseListener(this);
		
		grupo.add(rb_dni);
		grupo.add(rb_sinFiltro);
		
		panelfiltro.add(filtro);
		panelfiltro.add(rb_dni);
		panelfiltro.add(rb_sinFiltro);
	
		
		jp1.add(panelboton);
		jp1.add(panelfiltro);
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new FlowLayout());
		
		texto = new JTextField(10);
		
		bt_buscar = new JButton("BUSCAR");
		bt_buscar.setName("buscar");
		bt_buscar.addActionListener(this);
		
		jp2.add(texto);
		jp2.add(bt_buscar);
		
		add(scrollPane,BorderLayout.NORTH);
		add(jp1,BorderLayout.CENTER);
		add(jp2,BorderLayout.SOUTH);
        }
        
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton t = (JButton) e.getSource();
		String txt = t.getName();
		
		if(txt.equals("buscar")){	
			if(rb_dni.isSelected()){
				try {
					initClientesTable(user_control.buscarCliente(texto.getText()));
				}catch(IOException e2){
					showErrorMsg("Error en los archivos");
				}
			}
				
		}
		else if(txt.equals("ver")){
			Cliente cliente_aux = user_control.verCliente(dni);
			Usuario usuario_aux = user_control.consultarUsuario(dni);
			new VentanaVerCliente(cliente_aux, usuario_aux, user_control);
			try {
				initClientesTable(user_control.cargarClientes());
			}catch(IOException e2){
				showErrorMsg("Error en los archivos");
			}
		}
		else if(txt.equals("eliminar")){
			int seleccionada = JOptionPane.showConfirmDialog
					(this,"Eliminar", "Eliminar",JOptionPane.YES_NO_OPTION);
			if(seleccionada == JOptionPane.YES_OPTION){
				
				try {
					user_control.eliminarCliente(dni);
				} catch (IOException e1) {
					showErrorMsg("No se ha encontrado el fichero de usuarios y/o el de clientes.");
				}	
				
				try {
					initClientesTable(user_control.cargarClientes());
				}catch(IOException e3){
					showErrorMsg("Error al cargar los archivos");
				}
			
			}
			
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		 if(e.getSource() == jt){
		        int row = jt.rowAtPoint(e.getPoint());
		        if (row >= 0 && jt.isEnabled())
		        {
		        	bt_ver.setEnabled(true);
		        	bt_eliminar.setEnabled(true);
		        	dni = modelo.getValueAt(row,2).toString();
		        }
			 }
		 else if(e.getSource() == rb_sinFiltro){
				 texto.setText("");
				 try {
					initClientesTable(user_control.cargarClientes());
				} catch (IOException e1) {
					showErrorMsg("error, no se han podido cargar los clientes");
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

	
	public void showInfoMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}

	
	public void showErrorMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Error",JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Actualiza la tabla de clientes.
	 * @param lista_clientes Lista con la que rellenar la tabla.
	 */
	public void initClientesTable(List<Cliente> lista_clientes) {
		modelo.initClientesTable(lista_clientes);
	}

	/**
	 * Muestra la información de un cliente en una ventana nueva.
	 * @param c El cliente a mostrar.
	 * @param u El usuario del cliente a mostrar.
	 */
	public void verCliente(Cliente c, Usuario u) {
		new VentanaVerCliente(c, u, user_control);
	}

}
