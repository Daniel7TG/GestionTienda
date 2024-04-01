package app.util;

import java.awt.Image;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import app.modelos.DetallesCompra;
import app.modelos.DetallesVenta;
import app.modelos.Producto;

public class Util {

	public static final String RUTAIMG = "C:\\Users\\odtgo\\Desktop\\javaPrograma\\img";
	

	public static String capitalizeCammel(String text) {
		String initial = text.substring(0, 1).toUpperCase();
		String complete;
		if(text.length() > 1) {
			complete = text.substring(1, text.length());
		} else {
			complete = "";
		}
		return initial + complete;		
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

	
	public static String[][] detallesVentaToStringMat(List<DetallesVenta> list){
		String[][] matriz = new String[list.size()][DetallesVenta.class.getDeclaredFields().length];
		for(int i = 0; i < list.size(); i++) {
			matriz[i][0] = list.get(i).getCodigo();
			matriz[i][1] = String.valueOf(list.get(i).getCantidad());
			matriz[i][2] = String.valueOf(list.get(i).getPrecio());
			matriz[i][3] = String.valueOf(list.get(i).getTotal());
		}
		return matriz;
	}
	public static String[][] detallesToStringMat(List<DetallesCompra> list){
		String[][] matriz = new String[list.size()][DetallesCompra.class.getDeclaredFields().length];
		for(int i = 0; i < list.size(); i++) {
			matriz[i][0] = list.get(i).getCodigo();
			matriz[i][1] = String.valueOf(list.get(i).getCantidad());
			matriz[i][2] = String.valueOf(list.get(i).getPrecio());
			matriz[i][3] = String.valueOf(list.get(i).getTotal());
		}
		return matriz;		
	}
	public static String[][] toStringMat(List<Producto> list){
		String[][] matriz = new String[list.size()][Producto.class.getDeclaredFields().length];
		for(int i = 0; i < list.size(); i++) {
			matriz[i][0] = list.get(i).getCodigoBarras();
			matriz[i][1] = list.get(i).getNombre();
			matriz[i][2] = list.get(i).getMarca();
			matriz[i][3] = list.get(i).getContenido();
			matriz[i][4] = String.valueOf(list.get(i).getStockMaximo());
			matriz[i][5] = String.valueOf(list.get(i).getStockMinimo());
			matriz[i][6] = list.get(i).getTipo();
			matriz[i][7] = list.get(i).getUnidadDeMedida();	
			matriz[i][8] = list.get(i).getPresentacion();
			matriz[i][9] = list.get(i).getDescripcion();
		}
		return matriz;	
	}
	
	public static <T> Object[][] anyToString(List<T> list){
		if( list.size() == 0 ) return new String[][]{{}};
		
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
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return method;
	}
	
	
	
	
}
