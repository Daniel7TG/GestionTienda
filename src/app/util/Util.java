package app.util;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import app.abstractClasses.Detalles;
import app.interfaces.Funcionable;
import app.interfaces.Service;
import app.modelos.DetallesCompra;
import app.modelos.DetallesVenta;
import app.modelos.Producto;
import app.modelos.Proveedor;
import app.modelos.containers.Catalogo;

public class Util {

	public static final String RUTAIMG = "C:\\Users\\odtgo\\Desktop\\javaPrograma\\img";	
	
	public static String capitalizeCammel(String text) {
		if(text.length() == 0 ) return "";
		return text.substring(0, 1).toUpperCase() + text.substring(1, text.length());		
	}
	
	public static String capitalize(String text) {
		String[] subStrings = text.split(" ");
		int i = 0; 
		for(String item : subStrings) {
			if(item.length()>2) {
				StringBuilder sb = new StringBuilder(item.trim().toLowerCase());
				sb.setCharAt(0, Character.toUpperCase(item.charAt(0)));
				subStrings[i] = sb.toString();
			}
			i++;
		}
		String cadenaFinal = String.join(" ", subStrings);
		return cadenaFinal;
	}

	
	public static ImageIcon getImage(String path, int height, int width) {
		Image img= new ImageIcon(path).getImage();
		ImageIcon img2=new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
		return img2;
	}
	
	
	public static Integer[] range(int cantidad) {
		Integer[] numArray = new Integer[++cantidad];
		for(int i = 0; i < cantidad; i++) {
			numArray[i] = i;
		}
		return numArray;
	}

	
	
	public static <T> Object[][] getClassFields(List<T> list, Class<?>...classes){

		Map<Class<?>, Field[]> map = new HashMap<>();
		int columnSize = 0;
		for(Class<?> clazz : classes) {
			Field[] fields = clazz.getDeclaredFields();
			columnSize += fields.length;
			map.put(clazz, fields);
		}
		
		return anyToStringMap(list, map, columnSize, classes);
		
	}
	
	public static <T> Object[][] anyToStringMap(List<T> list, Map<Class<?>, Field[]> fields, int size, Class<?> ...classes){
		if( list.size() == 0 ) return new String[0][0];
		
		Object[][] matriz = new Object[list.size()][size];
		for(int i = 0; i < list.size(); i++) {
			int actualColumn = 0;
			for(Class<?> clazz : classes) {
				Field[] fieldActual = fields.get(clazz);
				for(int j = 0; j < fieldActual.length; j++, actualColumn++) {
					try {
						matriz[i][actualColumn] = getGetter(fieldActual[j], clazz).invoke((list.get(i)));
					} catch (IllegalArgumentException |IllegalAccessException |InvocationTargetException e) {
						e.printStackTrace();
					}					
				}
			}
		}
		return matriz;
	}
	
	
	public static <T> Object[][] anyToString(List<T> list){
		if( list.size() == 0 ) return new String[0][0];
		Class clazz = list.get(0).getClass();
		return anyToString(list, clazz);
	}
	
	
	public static <T> Object[][] anyToString(List<T> list, Class cl){
		Class clazz = cl;
		Field[] fields = clazz.getDeclaredFields();
		Object[][] matriz = new Object[list.size()][fields.length];
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < fields.length; j++) {
				try {
					matriz[i][j] = getGetter(fields[j], clazz).invoke((list.get(i)));
				} catch (IllegalArgumentException |IllegalAccessException |InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return matriz;
	}
	
	
	public static Method getGetter(Field field, Class clazz) {
		String methodName = "get" + capitalizeCammel(field.getName());
		Method method = null;
		try {
			method = clazz.getMethod(methodName);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return method;
	}

	
	private static String centerText( String texto, int espacio ) {
		return centerText(texto, espacio, false);
	}
	
	
	private static String centerText( String texto, int espacio, boolean first ) {
		int tlength = texto.length();
		if(tlength > espacio) return "_";
		int margin = (espacio - tlength) / 2;
		
		String centeredText = (first ? "<pre>" : "") + 
				" ".repeat(margin) + 
				texto + 
				" ".repeat(margin) + 
				( margin % 2 == 0 ? "" : " " );
		
		return centeredText + "<pre/>";
	}
	
	
	private static String formatProducto(int space, String codigo, String nombre, String cantidad, String precio, String total) {
		String finalText = "<pre>";
		int division = space/5;
		finalText = String.format("%-" + (division + 2) + "s", codigo); //codigo
		finalText += String.format("%-" + (division + 2) + "s", nombre); //nombre			
		finalText += String.format("%-" + (division - 2) + "s", cantidad + "u"); //cantidad	
		finalText += String.format("%-" + (division - 2) + "s", "$" + precio);	// precio
		finalText += String.format("%-" + (division) + "s", "$" + total); // total			
		return finalText + "<pre/>";	
	}
	
	
	public static String formatProduct(Detalles v, Service<Producto> cat, int space) {
		return formatProducto(space, 
				v.getCodigo(), 
				cat.get(v.getCodigo()).getNombre(), 
				String.valueOf(v.getCantidad()), 
				String.valueOf(v.getPrecio()), 
				String.valueOf(v.getTotal()));
	}
	
	
	private static String formatAny(int space, String...elementos) {
		StringBuilder finalText = new StringBuilder("<pre>");
		int division = space/elementos.length;
		for(String texto : elementos) {
			finalText.append(String.format( ("%-" + (division) + "s"), texto));
		}
		return finalText + "<pre/>";	
	}
	
	
	public static String generateTicket(List<? extends Detalles> detailsList, Service<Producto> catalogo,  List<String> headers) {
		StringBuilder ticket = new StringBuilder("<html>");
		int space = 60; 
		ticket.append( centerText(headers.get(0), space, true));
		for(int i = 1; i < headers.size(); i++) {
			ticket.append( centerText(headers.get(i), space) );
		}
		ticket.append("*".repeat(space));
		ticket.append(formatAny(space, "Codigo", "Nombre", "Cantidad", "Precio", "Total"));
		detailsList.forEach(detail -> ticket.append(formatProduct(detail, catalogo, space)));
		ticket.append(formatAny(space, "Total:", "", 
				String.valueOf(detailsList.stream().mapToInt(Detalles::getCantidad).sum()), "", 
				String.valueOf(detailsList.stream().mapToDouble(Detalles::getTotal).sum())));
		return String.valueOf(ticket) + "<html/>";
	}

	
	public static Function<JTextField, String[]> getNameFilter(Service<Producto> catalogo){
		return field -> 
				catalogo.getAll().stream()
				.map(Producto::getMainData)
				.filter(name -> name.toLowerCase().startsWith(field.getText().toLowerCase()))
				.toArray(String[]::new);
	}
	public static Function<JTextField, String[]> getCodeFilter(Service<Producto> catalogo){
		return field -> 
				catalogo.getAll().stream()
				.map(Producto::getCodigoBarras)
				.filter(code -> code.startsWith(field.getText()))
				.toArray(String[]::new);
	}	
	public static Function<JTextField, String[]> getRfcFilter(Service<Proveedor> proveedores){
		return field -> 
			proveedores.getAll().stream()
			.map(Proveedor::getRfc)
			.filter(rfc -> rfc.startsWith(field.getText()))
			.toArray(String[]::new);
	}	
	
	public static class FocusField implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			((JTextField)e.getSource()).transferFocus();
		}
	}
	
	
	public static class FocusBox implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			((JComboBox<?>)e.getSource()).transferFocus();
		}
	}

	
}
