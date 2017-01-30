
public class Ficha {
	private char simbolo;
	private int posY;
	private int posX;

	
	public int getPosY() {
		return posY;
	}

	public int getPosX() {
		return posX;
	}
	
	public Ficha(int y, int x){
		this.simbolo=' ';
		posY=y;
		posX=x;
	}
	
	public Ficha(int id){
		if(id==1)
			this.simbolo='X';
		else
			this.simbolo='O';
	}
	
	public char getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
	
	
	
	
	
	
}



