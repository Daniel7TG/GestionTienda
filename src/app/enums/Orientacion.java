package app.enums;

public enum Orientacion {
	NORTE, SUR, ORIENTE, PONIENTE;
	
	Orientacion() {
		
		
		
	}
	
	public static Orientacion getOrientacion(String orienta) {
		
		return Orientacion.valueOf(orienta);
//		Orientacion orientacion = switch (orienta) {
//			case "Norte" -> NORTE;
//			
//		
//		
//			default -> null;
//		};
		
	}
	
	public static void main(String[] args) {
		Orientacion orientacion = getOrientacion("hola");
		
	}
}
