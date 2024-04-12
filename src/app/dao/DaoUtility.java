package app.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import app.modelos.Producto;
import app.modelos.Vehiculo;
import app.modelos.VehiculoType;


public class DaoUtility {

	

	private static List<String> readFile(String url) {
		List<String> lista = new ArrayList<>();

		File file = new File(url);
		Scanner entrada;
		
		if(!file.isFile() | !file.exists()) return null;
			
		try {
			entrada = new Scanner(file);
			while(entrada.hasNext()) {
				lista.add(entrada.nextLine());
			}
			entrada.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	
	private static <T> String convertObj2Str(T object, List<Method> methods) {	
		StringBuilder strObject = new StringBuilder();
		methods.forEach(m -> {
			try {
				strObject.append( m.invoke(object) + "\t");
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		});
		return strObject + "\n";
	}
	
	/**
	 * Returns a String with every object formated with {@code \t} after an atribute and {@code \n} after each object.
	 * The order of the atributes on the String will be the same as in the parameter declaration
	 * @param <T>  
	 * @param list to convert
	 * @return a String formatted
	 */
	public static <T> String listToString(List<T> list) {
		if(list.size() <= 0) return "";
		
		StringBuilder strTotal = new StringBuilder(); 
		Class<?> clazz = list.get(0).getClass();	
		
		// Obtener solo el nombre de cada atributo (ordenado)
        List<String> nameFields = Stream.of(clazz.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
        
        // Obtener todos los metodos get
		List<Method> getMethods = 
				Stream.of(clazz.getDeclaredMethods())
				.filter(m -> m.getName().startsWith("get"))
				.collect(Collectors.toList());
		
		// Ordena los metodos segun nameFields
		Comparator<Method> comp = new Comparator<Method>() {
			public int compare(Method methodA, Method methodB) {
				String nameA = methodA.getName().substring(3).toLowerCase();
				String nameB = methodB.getName().substring(3).toLowerCase();
				if( nameFields.indexOf(nameA) > nameFields.indexOf(nameB) ) return 1;
				else return -1;
			}
		};
		Collections.sort(getMethods, comp);		

		// Aplica los metodos a cada objeto
		list.forEach( item -> strTotal.append(convertObj2Str(item, getMethods)) );
		
		return strTotal.toString();
	}
	
	
	public static void writeFile(String content, String filename) {
		File file = new File("resources/%s.txt".formatted(filename));
		try {
			FileWriter writer = new FileWriter(file);
			writer.write( content );
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	
	private static List<String> getTextContent(String fileName){
		return readFile(fileName);
	}
	
	public static List<String> getMedidas(){
		return getTextContent("resources/raw/medidas.txt");
	}
	public static List<String> getTipos(){
		return getTextContent("resources/raw/tipos.txt");
	}
	public static List<String> getPresentaciones(){
		return getTextContent("resources/raw/presentaciones.txt");
	}
	
	public static List<Producto> getVehicles(String url) {
		List<String> data = readFile(url);
		if(data.size() > 0) {
			List<Producto> autos = new ArrayList<Producto>();
		for(int i = 0; i < data.size(); i++) {
			autos.add(createProducto(data.get(i)));
		}
		return autos;
		}else {
			return null;
		}
	}
	
	
	private static Producto createProducto(String data) {
		String args[] = data.split("\t");
		return new Producto(
				args[0],
				args[1],
				args[2],
				args[3],
				args[4],
				args[5],
				args[6],
				Integer.valueOf(args[7]),
				Integer.valueOf(args[8]),
				args[9],
				Double.valueOf(args[10])
				);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
