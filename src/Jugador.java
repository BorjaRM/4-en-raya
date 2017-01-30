public class Jugador {
	private int id;
	private String nombre;
	private Ficha ficha;
	
	public Jugador(int id){ 
		this.id = id;
		this.ficha=new Ficha(this.id);
	}
	
	public Ficha getFicha() {
		return ficha;
	}

	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
