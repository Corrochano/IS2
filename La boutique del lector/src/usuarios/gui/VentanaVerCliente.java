package usuarios.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.GUI;
import usuarios.Cliente;
import usuarios.Usuario;
import usuarios.controller.UsuariosController;

/**
 * Vnetana que extiende a GUI, muestra toda la informacion sobre un cliente,
 * implementa ActionListener,ListSelectionListener,ClientesObserver
 */
public class VentanaVerCliente extends GUI implements ActionListener, ListSelectionListener{
	
	private static final long serialVersionUID = 1L;
	
	private UsuariosController user_control;
	private JPanel panel;
	private Cliente cliente;
	private Usuario usuario;
	private JLabel nom, ape, dni, email, contra;
	private JTextField tf_nom, tf_ape, tf_dni,tf_email, tf_contra; 
	private JButton bt_editar, bt_guardar;
	
	/**
	 * Constructor con parámetros
	 * @param c cliente
	 * @param u usuario
	 * @param user_control admincontroller
	 */
	public VentanaVerCliente(Cliente c, Usuario u, UsuariosController uc) {
		super("Cliente" + ": " + c.getNombre() + " " + c.getApellidos());
		cliente = c;
		usuario = u;
		user_control = uc;
		initVentanaVercliente();
	}

	/**
	 * Inicializa todos los componentes
	 */
	private void initVentanaVercliente() {
		panelPrincipal = this.getContentPane();
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Datos cliente");
		panel.setBorder(titulo);
		
		JPanel datos = new JPanel();
		datos.setLayout(new GridLayout(9,2));
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());
		
		nom = new JLabel("Nombre");
		tf_nom = new JTextField(10);
		tf_nom.setText(cliente.getNombre());
		tf_nom.setEditable(false);
		
		ape = new JLabel("Apellido");
		tf_ape = new JTextField(15);
		tf_ape.setText(cliente.getApellidos());
		tf_ape.setEditable(false);
		
		dni = new JLabel("DNI");
		tf_dni = new JTextField(10);
		tf_dni.setText(cliente.getDNI());
		tf_dni.setEditable(false);
		
		
		email = new JLabel("Email");
		tf_email = new JTextField(10);
		tf_email.setText(cliente.getEmail());
		tf_email.setEditable(false);
		
		contra = new JLabel("Contraseña");
		tf_contra = new JTextField(10);
		tf_contra.setEditable(false);
		tf_contra.setText(usuario.getPass());
		
		bt_editar = new JButton("Editar");
		bt_editar.setName("editar");
		bt_editar.addActionListener(this);
		
		bt_guardar = new JButton("Guardar");
		bt_guardar.setName("guardar");
		bt_guardar.setVisible(false);
		bt_guardar.addActionListener(this);
		
		datos.add(nom);
		datos.add(tf_nom);
		datos.add(ape);
		datos.add(tf_ape);
		
		datos.add(dni);
		datos.add(tf_dni);
		
		
		datos.add(email);
		datos.add(tf_email);
		datos.add(contra);
		datos.add(tf_contra);
		
		botones.add(bt_editar);
		botones.add(bt_guardar);
		
		panel.add(datos, BorderLayout.NORTH);
		panel.add(botones, BorderLayout.SOUTH);
		
		
		panelPrincipal.add(panel);
		
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		
	}

	/**
	 * Abre un dialogo de informacion con el mensaje
	 * @param msg mensaje a mostrar
	 */
	public void showInfoMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Informacion", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Abre un dialogo de error con el mensaje
	 * @param msg mensaje a mostrar
	 */
	public void showErrorMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Error",JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {}

	/**
	 * Activa los campos de la ventana
	 */
	private void activarCampos(){
		bt_guardar.setVisible(true);
		tf_nom.setEditable(true);
		tf_ape.setEditable(true);
		tf_contra.setEditable(true);	
		tf_email.setEditable(true);
		bt_editar.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton t = (JButton) e.getSource();
		String txt = t.getName();
		
		if(txt.equals("editar")){
			activarCampos();
		}
		else if(txt.equals("guardar")){
			
			String name = tf_nom.getText();
			String apell = tf_ape.getText();
			String dni = tf_dni.getText();
			String email = tf_email.getText();
			String rol = "cliente";
			Usuario u = null;
			String user_dni = tf_dni.getText();;
			String password = tf_contra.getText();

			if(!user_dni.equals("") && !password.equals("")){
				u = new Usuario(user_dni,password,rol);
			}
			int seleccionada = JOptionPane.showConfirmDialog
					(this,"Desea continuar", "Actualizar",JOptionPane.YES_NO_OPTION);
			if(seleccionada == JOptionPane.YES_OPTION){
			   user_control.modificarCliente(new Cliente(name, apell, dni, email), u);
		   }
		}
	}
}
