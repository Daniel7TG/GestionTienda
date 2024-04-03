package app.util;

import java.awt.Image;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.DocumentFilter;
import javax.swing.text.NumberFormatter;

import app.modelos.DetallesCompra;
import app.modelos.DetallesVenta;
import app.modelos.Producto;

public class Util {

	public static final String RUTAIMG = "C:\\Users\\odtgo\\Desktop\\javaPrograma\\img";
	
	static UnaryOperator<AbstractFormatter> doubleFilter = change -> {
        return new NumberFormatter() {
            public Object stringToValue(String text) throws java.text.ParseException {
                if (text.matches("^[0-9]+(\\.[0-9]*)?$")) {
                    return super.stringToValue(text);
                } else {
                    throw new java.text.ParseException("Solo se permiten números", 0);
                }
            }
        };
	};

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
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return method;
	}
	

	public static void restrictNumbers(JSpinner spin, SpinnerNumberModel model) {
	
		String decimalFormatPattern = "#######.#";
		((NumberEditor) spin.getEditor()).getTextField().setFormatterFactory(
                new DefaultFormatterFactory(new MyListFormatter(model, new DecimalFormat(decimalFormatPattern))));
//        JFormattedTextField textField = ((JSpinner.DefaultEditor) spin.getEditor()).getTextField();
//        textField.setFormatterFactory(new DefaultFormatterFactory(doubleFilter.apply(textField.getFormatter())));

	}

	static class MyListFormatter extends NumberFormatter {

		private static final long serialVersionUID = -790552903800038787L;
		private final SpinnerNumberModel model;
		private DocumentFilter filter;

		MyListFormatter(SpinnerNumberModel model, NumberFormat format) {
			super(format);
			this.model = model;
			setValueClass(model.getValue().getClass());
		}

		public void setMinimum(Comparable min) {
			model.setMinimum(min);
		}

		public Comparable getMinimum() {
			return model.getMinimum();
		}

		public void setMaximum(Comparable max) {
			model.setMaximum(max);
		}

		public Comparable getMaximum() {
			return model.getMaximum();
		}

		protected DocumentFilter getDocumentFilter() {
			if (filter == null) {
				filter = new MyDocumentFilter();
			}
			return filter;
		}
	}
	
	
	static class MyDocumentFilter extends DocumentFilter {
//		public final static String REGEX_DOUBLE_NUMBER = "^([0-9]+)(\\.(\\d{1})?)?$";
		public final static String REGEX_DOUBLE_NUMBER = "^[0-9]+(\\.[0-9]*)?$";

		public void replace(FilterBypass fb, int offset, int length, String text,
				AttributeSet attrs) throws BadLocationException {

			String mytext = fb.getDocument().getText(0, offset);
			mytext += text;
			if (fb.getDocument().getLength() - 1 > offset) {
				mytext += fb.getDocument().getText(offset + 1,
						fb.getDocument().getLength() - offset);
			}
			boolean ok = true;

			ok = Pattern.matches(REGEX_DOUBLE_NUMBER, mytext);
			if (ok)
				super.replace(fb, offset, length, text, attrs);

		}
	}

	
	
}
