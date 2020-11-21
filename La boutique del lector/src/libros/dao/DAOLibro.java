package libros.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import libros.CategoriaEnum;
import libros.Libro;

/**
 * Clase que representa el DAO deun libro
 */
public class DAOLibro implements IDAOLibro {
	private List<Libro> lista_libros = new ArrayList<>();

	@Override
	public void eAltaLibro(Libro l){
		if (!eBuscarLibro(l.get_id())) {
			lista_libros.add(l);
			guardarDatosLibros();
		}
		
	}

	@Override
	public void eEliminarLibro(String id) {
			for(int i=0;i<lista_libros.size();i++) {
				if(id.equals(lista_libros.get(i).get_id())) {
					lista_libros.remove(lista_libros.get(i));
				}
			}
			guardarDatosLibros();
	}

	@Override
	public boolean eBuscarLibro(String id) {
		FileInputStream libros = null;
		try {
			libros = new FileInputStream("ficheros/libros.txt");
			lista_libros = cargarLibros(libros);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Libro lib: lista_libros) {
			if(id.equalsIgnoreCase(lib.get_id())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void eModificarLibro(Libro libro) throws IOException {
		if(eBuscarLibro(libro.get_id())) {
			eEliminarLibro(libro.get_id());
			eAltaLibro(libro);
			guardarDatosLibros();
		}
		
	}

	@Override
	public Libro eConsultarLibro(String id) {
		FileInputStream libros = null;
		try {
			libros = new FileInputStream("ficheros/libros.txt");
			lista_libros =  cargarLibros(libros);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Libro lib: lista_libros) {
			if(id.equals(lib.get_id())) {
				return lib;
			}
		}
		return null;
		
	}
	
	@Override
	public void guardarDatosLibros() {
		
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
	
	
	
	@Override
	public Libro verLibro(String id) {
		return eConsultarLibro(id);
	}

	@Override
	public List<Libro> eBuscarLibroTit(String tit) throws IOException {
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
	
	@Override
	public List<Libro> eBuscarLibroCat(String cat) throws IOException{
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
}
