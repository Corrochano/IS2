package ventas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import libros.CategoriaEnum;
import libros.Libro;
import ventas.Venta;

/**
 * Clase que representa el dao de una venta
 */
public class DAOVenta implements IDAOVenta{

	private List<Venta> lista_ventas = new ArrayList<Venta>();
	private List<Libro> lista_libros = new ArrayList<Libro>();
	
	@Override
	public void eNuevaVenta(Venta v) {
		if (!eBuscarVenta(v.getId())) {
			lista_ventas.add(v);
			guardarVentas();
		}
	}

	@Override
	public void eEliminarVenta(String id) throws IOException {
		FileInputStream ventas = null;
		ventas = new FileInputStream("ficheros/ventas.txt");
		lista_ventas = cargarVentas(ventas);
		for(int i=0;i<lista_ventas.size();i++) {
			if(id.equals(lista_ventas.get(i).getId())) {
				lista_ventas.remove(lista_ventas.get(i));
			}
		}
		guardarVentas();
	}

	@Override
	public boolean eBuscarVenta(String id) {
		FileInputStream ventas = null;
		try {
			ventas = new FileInputStream("ficheros/ventas.txt");
			lista_ventas = cargarVentas(ventas);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Venta ven: lista_ventas) {
			if(id.equalsIgnoreCase(ven.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void eModificarVenta(Venta v) throws IOException {
		if(eBuscarVenta(v.getId())) {
			eEliminarVenta(v.getId());
			eNuevaVenta(v);
			guardarVentas();
		}
	}

	@Override
	public List<Venta> eConsultarVenta(String id) {
		FileInputStream ventas = null;
		try {
			ventas = new FileInputStream("ficheros/ventas.txt");
			lista_ventas =  cargarVentas(ventas);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Venta ven: lista_ventas) {
			if(id.equals(ven.getId())) {
				List<Venta> ret = new ArrayList<Venta>();
				ret.add(ven);
				return ret;
			}
		}
		return null;
	}
	
	@Override
	public List<Libro> eConsultarLibro(String id) {
		FileInputStream libros = null;
		try {
			libros = new FileInputStream("ficheros/libros.txt");
			lista_libros =  cargarLibros(libros);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Libro lib: lista_libros) {
			if(id.equals(lib.get_id())) {
				List<Libro> ret = new ArrayList<Libro>();
				ret.add(lib);
				return ret;
			}
		}
		return null;
	}

	/**
	 * Carga los datos del fichero 'ventas.txt'
	 * @param file fichero de ventas
	 * @return una lista de ventas
	 * @throws IOException io exception
	 */
	@SuppressWarnings("deprecation")
	public List<Venta> cargarVentas(InputStream file) throws IOException {
	
		List<Venta> lista_ventas = new ArrayList<Venta>();
		
		String id;
		Venta vent;;
		int precio;
		String dniCli;
		Date fecha;
		String tituloLibro;
		 
		String line = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(file));
		 
				 while((line = br.readLine()) != null){
					 String[] w = line.split(" ");
						 if(w.length == 7){
							dniCli = w[3];
							
							fecha = new Date();
							fecha.setDate(Integer.parseInt(w[4]));
							fecha.setMonth(Integer.parseInt(w[5]));
							fecha.setYear(Integer.parseInt(w[6]));
		
							precio = Integer.parseInt(w[2]);
							
							tituloLibro = w[1];
							
							id = w[0];
							
							vent = new Venta(id, tituloLibro, precio, dniCli, fecha);
							lista_ventas.add(vent);
						 }
						 
					 }
					 			 	
				 br.close();
		
		return lista_ventas;
	}

	@Override
	public List<Libro> cargarLibros(FileInputStream libros) throws IOException {
		List<Libro> lista_libros = new ArrayList<Libro>();
		
		 String line = "";
		 BufferedReader br = new BufferedReader(new InputStreamReader(libros));
		 
				 while((line = br.readLine()) != null){
					 String[] w = line.split(" ");
						 if(w.length == 5){
							 w[1] = w[1].replace("_"," ");
							 w[3] = w[3].replace("_"," ");
							 lista_libros.add(new Libro(w[0],w[1], Integer.parseInt(w[2]) ,w[3],CategoriaEnum.valueOf(w[4]))); 
						 }
						 
					 }
					 			 	
				 br.close();
		
		
		return lista_libros;
	}

	/**
	 * Guarda los datos en ventas.txt
	 */
	public void guardarVentas() {
		FileOutputStream file = null;
		try {
			file = new FileOutputStream("ficheros/ventas.txt");
			BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(file));
			
				for(int i=0 ; i < lista_ventas.size(); i++){
					buffer.write(lista_ventas.get(i).toString() + "\n");
				}
				
				buffer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Venta> eBuscarVentaCliente(String text) {
		FileInputStream ventas = null;
		List<Venta> aux = new ArrayList<Venta>();
		try {
			ventas = new FileInputStream("ficheros/ventas.txt");
			lista_ventas = cargarVentas(ventas);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Venta ven: lista_ventas) {
			if(text.equalsIgnoreCase(ven.getCliente())) {
				aux.add(ven);
			}
		}
		return aux;
	}

	@Override
	public void eliminarLibro(String id) {
		for(int i=0;i<lista_libros.size();i++) {
			if(id.equals(lista_libros.get(i).get_id())) {
				lista_libros.remove(lista_libros.get(i));
			}
		}
		guardarDatosLibros();
	}
	
	/**
	 * Guarda los datos pertenecientes a dao de ventas en libros.txt
	 */
	private void guardarDatosLibros() {
		
		FileOutputStream file = null;
		try {
			file = new FileOutputStream("ficheros/libros.txt");
			BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(file));
				
				for(int i=0 ; i < lista_libros.size(); i++){
					buffer.write(lista_libros.get(i) + "\n");
				}
				
				buffer.close();
				
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public List<Libro> buscarLibroCat(String cat) throws IOException{
		List<Libro> lib = new ArrayList<Libro>();
		
		FileInputStream libros = null;
		
		libros = new FileInputStream ("ficheros/libros.txt");
		lista_libros = cargarLibros(libros);
		
		for (Libro l : lista_libros) {
			if(l.get_categoria().toString().equalsIgnoreCase(cat)) {
				lib.add(l);
			}
		}
		return lib;
	}
	
	@Override
	public List<Libro> buscarLibroTit(String tit) throws IOException {
		List<Libro> lib = new ArrayList<Libro>();
		
		FileInputStream libros = null;
		
		libros = new FileInputStream("ficheros/libros.txt");
		lista_libros = cargarLibros(libros);
		
		for(Libro l : lista_libros){
			if(l.get_titulo().equalsIgnoreCase(tit)){
				lib.add(l);
			}
		}
		
		return lib;
	}
	
}
