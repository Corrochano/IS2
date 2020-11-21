package main;

import usuarios.controller.UsuariosController;
import views.LoginGui;

/**
 * Clase Main del programa
 */
public class Main {

	/**
	 * Constructora con parametros
	 * @param args argumentos por parametro (No necesarios)
	 */
	public static void main(String[] args) {
		UsuariosController controlador = new UsuariosController();
		new LoginGui(controlador);
	}

}