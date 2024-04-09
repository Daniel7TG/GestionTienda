package app.util;

import java.awt.Image;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import app.modelos.DetallesVenta;

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

	
	public static <T> Object[][] anyToString(List<T> list){
		if( list.size() == 0 ) return new String[0][0];
		
		Class clazz = list.get(0).getClass();
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
		int tlength = texto.length();
		if(tlength > espacio) return "_";
		int margin = (espacio - tlength) / 2;
		
		String centeredText = (texto.equals("Tienda") ? "<pre>" : "") + 
				" ".repeat(margin) + 
				texto + 
				" ".repeat(margin) + 
				( margin % 2 == 0 ? "" : " " );
	
		return centeredText + "<pre/>";
	}
	
	
	// Sobrecargar metodo para hacerlo con detalles compra 
	private static String formatProduct(DetallesVenta details, int space) {
		String finalText = "<pre>";
		StringBuilder textoLeft = new StringBuilder(details.getCodigo());
		int middle = space/2;
		
		if(textoLeft.length() > middle) {
			finalText = textoLeft.substring(0, middle) + "  ";
		}
		else {
			finalText = String.format( ("%-" + (middle + 2) + "s"), textoLeft.toString());
		}				
		finalText += String.format("%5s", details.getCantidad()+"u");
		finalText += String.format("%" + (middle-7) + "s", "$" + details.getTotal()) + "<pre/>";	
		return finalText;	
	}
	
	
	// agregar ticketHeader como array de strings para formatear 
	public static String generateTicket(ArrayList<DetallesVenta> detailsList) {
		StringBuilder ticket = new StringBuilder("<html>");
		ticket.append( centerText("Tienda", 35) );
		ticket.append( centerText("Tel: 55-5555-5555", 35) );
		ticket.append( centerText("Suc. CDMX", 35) );
		
		
		detailsList.forEach(detail -> ticket.append(formatProduct(detail, 35)));
		ticket.append(formatProduct(
				new DetallesVenta("Total", detailsList.stream().mapToDouble(DetallesVenta::getTotal).sum(), 1)
				, 35));
		return String.valueOf(ticket) + "<html/>";
	}
	
}
