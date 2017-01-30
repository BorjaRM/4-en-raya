
public class Tablero {
	private String cabecera;
	private Ficha[][] tablero;

	
	public Tablero(int n_filas, int n_columnas){
		this.cabecera= "|   A" +"   " +"B" +"   " +"C" +"   " +"D" +"   " +"E" +"   " +"F" +"   " +"|";
		
		if(n_filas<4||n_columnas<4){
			System.out.println("El tamaño minimo del tablero es de 4x4");
			tablero=null;
		}
		else
			this.tablero=new Ficha [n_filas][n_columnas];
	}

	public String getCabecera(){
		return cabecera;
	}

	public Ficha[][] getTablero(){
		return tablero;
	}
	
	public void rellenaTableroVacio(){
		for(int i=0;i<tablero.length;i++){
			for(int j=0;j<tablero[i].length;j++){
				tablero[i][j]=new Ficha(i,j);
			}
		}
	}

	public void imprimeTablero(){
		System.out.println(cabecera);
		for(int i=0;i<tablero.length;i++){
			System.out.print("|   ");
			for(int j=0;j<tablero[i].length;j++){
				System.out.print(tablero[i][j].getSimbolo() +"   ");
			}
			System.out.println("|");
		}
	}
	
	public boolean compruebaColumnaLibre(int valorLetra){
		boolean columnaLlena=true;
				
		for(int i=0;i<tablero.length;i++){
			Ficha posicionAComprobar=tablero[i][valorLetra];
			if(posicionAComprobar.getSimbolo()==' '){
				columnaLlena=false;
			}
		}
		return columnaLlena;
	}
	
	public boolean comprueba4EnRaya(Ficha f, Jugador j){
		boolean h = compruebaHorizontal(f,j);
		boolean v = compruebaVertical(f,j);
		boolean dd = compruebaDiagonalDerecha(f,j);
		boolean de = compruebaDiagonalIzquierda(f,j);
		
		if(h==true||v==true||dd==true||de==true){
			System.out.println(j.getNombre().toUpperCase() +" HA GANADO LA PARTIDA!!");
			return true;
		}else
			return false;
	}
	
	public int convierteDeLetraANumero(char posicion){ //intentar modificar esto para q pueda variar (codigos ascii)
		int columna=-1;		
		switch(posicion){
			case 'A': columna=0; break;
			case 'B': columna=1; break;
			case 'C': columna=2; break;
			case 'D': columna=3; break;
			case 'E': columna=4; break;
			case 'F': columna=5; break;
		}
		return columna;
	}
	
	public Ficha colocaFichaEnTablero(Jugador j, int valorLetra){
		Ficha posicionAOcupar=null;
		
		for(int i=0;i<tablero.length;i++){
			if(tablero[i][valorLetra].getSimbolo()==' ')
				posicionAOcupar=tablero[i][valorLetra];
		}
		posicionAOcupar.setSimbolo(j.getFicha().getSimbolo());
		return posicionAOcupar;
	}
	
	private boolean compruebaHorizontal(Ficha f, Jugador j){
		int contador=1;
		int x=f.getPosX();
		int y=f.getPosY();
		int ancho=tablero[y].length;
		
		while(x+1<ancho && tablero[y][x+1].getSimbolo() == j.getFicha().getSimbolo() && contador<4){ //derecha
			x++;
			contador++;
		}		
		x=f.getPosX();
		
		while(x-1>=0 && tablero[y][x-1].getSimbolo() == j.getFicha().getSimbolo() && contador<4){ //izquierda
			x--;
			contador++;
		}
		
		if(contador>=4)
			return true;
		return false;		
	}
	
	private boolean compruebaVertical(Ficha f, Jugador j){
		int contador=1;
		int x=f.getPosX();
		int y=f.getPosY();
		int largo=tablero.length;

		while(y+1<largo && tablero[y+1][x].getSimbolo() == j.getFicha().getSimbolo() && contador<4){ //derecha
			y++;
			contador++;
		}		
				
		if(contador>=4)
			return true;
		return false;		
	}
	
	private boolean compruebaDiagonalDerecha(Ficha f, Jugador j){
		int contador=1;
		int x=f.getPosX();
		int y=f.getPosY();
		int ancho=tablero[y].length;
		int largo=tablero.length;


		while((x+1<ancho && y-1>0) && tablero[y-1][x+1].getSimbolo() == j.getFicha().getSimbolo() && contador<4 ){//derecha y arriba
			y--;
			x++;
			contador++;
		}
		
		x=f.getPosX();
		y=f.getPosY();
		while((x-1>=0 && y+1<largo) && tablero[y+1][x-1].getSimbolo() == j.getFicha().getSimbolo() && contador<4 ){//izquierda y abajo
			y++;
			x--;
			contador++;
		}
		
		if(contador>=4)
			return true;
		
		return false;
	}
	
	private boolean compruebaDiagonalIzquierda(Ficha f, Jugador j){
		int contador=1;
		int x=f.getPosX();
		int y=f.getPosY();
		int ancho=tablero[y].length;
		int largo=tablero.length;

		while((x-1>=0 && y-1>=0) && tablero[y-1][x-1].getSimbolo() == j.getFicha().getSimbolo() && contador<4 ){//izquierda y arriba
			y--;
			x--;
			contador++;
		}
		
		x=f.getPosX();
		y=f.getPosY();
		while((x+1<ancho && y+1<largo) && tablero[y+1][x+1].getSimbolo() == j.getFicha().getSimbolo() && contador<4 ){//derecha y abajo
			y++;
			x++;
			contador++;
		}
		
		if(contador>=4)
			return true;
		return false;
	}
	
	public boolean compruebaMatrizLlena(){
		int contador=0;
		
		for(int i=0;i<tablero.length;i++){
			for(int j=0;j<tablero[i].length;j++){
				if(tablero[i][j].getSimbolo()==' ')
					contador++;
			}
		}
		if(contador==0){
			System.out.println("No quedan casillas libres, FIN DEL JUEGO!!");
			return true;
		}
		return false;
		
	}

	
	
}

