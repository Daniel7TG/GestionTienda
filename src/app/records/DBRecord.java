package app.records;

public record DBRecord(String dataBase, String usuario, String password, String driver, String protocolo) {
	public void a() {
		System.out.println("aaaaaa");
		System.out.println(dataBase);		
	}
}
