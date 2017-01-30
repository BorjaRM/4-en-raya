import java.util.Scanner;

public class Inicio {
	private Scanner sc;
	private boolean hayGanador;
	private boolean matrizLlena;

	public static void main(String[] args) {
		Inicio juego = new Inicio();
		
		//Creamos los jugadores y les damos un nombre
		Jugador jugador1 = new Jugador(1);
		jugador1.setNombre(juego.pideNombreJugador("Introduce un nombre para el Jugador "+jugador1.getId()));
		Jugador jugador2 = new Jugador(2);
		jugador2.setNombre(juego.pideNombreJugador("Introduce un nombre para el Jugador "+jugador2.getId()));
		
		//Creamos el tablero
		Tablero tablero = new Tablero(5,6);
		if(tablero.getTablero()!=null){
			tablero.rellenaTableroVacio();
			tablero.imprimeTablero();
			System.out.println("");	
		
			//Indica que ficha tiene asociada cada jugador
			System.out.println(jugador1.getNombre() +" juega con " +"\"" +jugador1.getFicha().getSimbolo()  +"\"");
			System.out.println(jugador2.getNombre() +" juega con " +"\"" +jugador2.getFicha().getSimbolo()  +"\"");
			
			//Comienza el juego
			boolean turnoJugador1=false;
			
			do{
				turnoJugador1=juego.pasaTurno(turnoJugador1);
				
				if(turnoJugador1){
					juego.jugada(jugador1,juego,tablero);
				}else{
					juego.jugada(jugador2,juego,tablero);
				}
				tablero.imprimeTablero();
			}while(!juego.hayGanador && !juego.matrizLlena);
		}
	}
	
	public Inicio(){
		this.sc=new Scanner(System.in);		
		this.hayGanador=false;
		this.matrizLlena=false;
	}
	
	public String pideNombreJugador(String msj){
		System.out.println(msj);
		String valor= sc.nextLine();
		return valor;
	}		
	
	public void jugada(Jugador j,Inicio elJuego, Tablero elTablero){
		String columnas="ABCDEF";
		char columnaElegida=' ';
		int valorLetra;
		boolean columnaLlena=false;
		Ficha ficha;
		
		System.out.println("Es el turno de " +j.getNombre());
		
		do{
			columnaElegida=elJuego.pidePosicionParaColocarFicha();
			if(columnas.indexOf(columnaElegida)!=-1){
				valorLetra=elTablero.convierteDeLetraANumero(columnaElegida);
				columnaLlena=elTablero.compruebaColumnaLibre(valorLetra);
				if(columnaLlena)
					System.out.println("Columna llena, elige otra posicion");
				else{
					ficha=elTablero.colocaFichaEnTablero(j,valorLetra);
					elJuego.hayGanador=elTablero.comprueba4EnRaya(ficha, j);
					elJuego.matrizLlena=elTablero.compruebaMatrizLlena();
				}					
			}else{ //La letra no se corresponde con ninguna columna
				System.out.println("Elige una posicion valida");
			}			
		}while(columnas.indexOf(columnaElegida)==-1 || columnaLlena);	
	}

	public char pidePosicionParaColocarFicha(){
		System.out.println("¿Donde quieres colocar tu ficha?");
		char valor= sc.next().toUpperCase().charAt(0);
		return valor;
	}
	
	public boolean pasaTurno(boolean turnoJugador1){ //Cambia el turno al otro jugador
		if(turnoJugador1)
			return turnoJugador1=false;
		else
			return turnoJugador1=true;		
	}
	
}
