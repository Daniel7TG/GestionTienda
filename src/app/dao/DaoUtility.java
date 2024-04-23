package app.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
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
import app.util.Util;


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
	
	
	public static <T> String anyToString(List<T> list, String ...fields){
		if( list.size() == 0 ) return "";
		Class clazz = list.get(0).getClass();
		return anyToString(list, clazz, fields);
	}
	public static <T> String anyToString(List<T> list, Class cl, String ...extraFields){
		StringBuilder finalString = new StringBuilder();
		Class clazz = cl;
		Field[] fields = clazz.getDeclaredFields();
		if(extraFields.length > 0) {
			Field[] extraField = 
			Arrays.stream(extraFields)
			.map(t -> {
					try {return clazz.getDeclaredField(t);}
					catch (NoSuchFieldException | SecurityException e) {e.printStackTrace();}
					return null;
				})
			.toArray(Field[]::new);
		    Field[] newFields = Arrays.copyOf(fields, fields.length + extraField.length);
		    System.arraycopy(extraField, 0, newFields, fields.length, extraField.length);
		    fields = newFields;
		}
		
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < fields.length; j++) {
				try {
					finalString.append(getGetter(fields[j], clazz).invoke((list.get(i))) + "\t");
				} catch (IllegalArgumentException |IllegalAccessException |InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			finalString.append("\n");
		}
		return finalString.toString();
	}
	public static Method getGetter(Field field, Class clazz) {
		String methodName = "get" + Util.capitalizeCammel(field.getName());
		Method method = null;
		try {
			method = clazz.getMethod(methodName);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return method;
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
	
	
	
	public static <T> void saveFileTxt(List<T> list, String name, String...extraFields) {
		if(list.isEmpty())
			writeFile("", name);
		else 
			writeFile(anyToString(list, list.get(0).getClass(), extraFields ), name);
	
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
	public static List<Producto> getProductos(){
		return getProductos("resources/raw/productos.txt");
	}
//	public static List<Proveedor> getProveedores(){
//		return getProveedores("resources/raw/productos.txt");
//	}
//	public static List<Compra> getCompras(){
//		return getCompras("resources/raw/productos.txt");
//	}
//	public static List<Venta> getVentas(){
//		return getVentas("resources/raw/productos.txt");
//	}
	
	public static List<Producto> getProductos(String url) {
		List<String> data = readFile(url);
		if(data.size() > 0) {
			List<Producto> prods = new ArrayList<Producto>();
		for(int i = 0; i < data.size(); i++) {
			prods.add(createProducto(data.get(i)));
		}
		return prods;
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
