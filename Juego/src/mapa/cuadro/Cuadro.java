package mapa.cuadro;

import graficos.HojaSprites;
import graficos.Pantalla;
import graficos.Sprite;

public class Cuadro {
	private int x;
	private int y;
	
	public Sprite sprite;
	public static final int LADO=32;
	//Coleccion de cuadros
	public static final Cuadro VACIO = new Cuadro(Sprite.VACIO);
	public static final Cuadro ASFALTO=new Cuadro(Sprite.ASFALTO);
	
	public static final Cuadro ARENA = new Cuadro(Sprite.ARENA);
	public static final Cuadro BORDE_CARRETERA = new Cuadro(Sprite.BORDE_CARRETERA);
	public static final Cuadro BORDE_CARRETERAB = new Cuadro(Sprite.BORDE_CARRETERAB);
	public static final Cuadro CENTRO_CARRETERA = new Cuadro(Sprite.CENTRO_CARRETERA);
	public static final Cuadro ESQUINA_CARRETERA = new Cuadro(Sprite.ESQUINA_CARRETERA);
	public static final Cuadro PARED_PIEDRA = new Cuadro(Sprite.PARED_PIEDRA);
	public static final Cuadro PARED_PIEDRA_INFERIOR = new Cuadro(Sprite.PARED_PIEDRA_INFERIOR);
	public static final Cuadro PARED_PIEDRA_CARRETERA = new Cuadro(Sprite.PARED_PIEDRA_CARRETERA);
	public static final Cuadro PARED_PIEDRA_CARRETERAB = new Cuadro(Sprite.PARED_PIEDRA_CARRETERAB);
	public static final Cuadro PUERTA_SUPERIOR_IZQUIERDA = new Cuadro(Sprite.PUERTA_SUPERIOR_IZQUIERDA);
	public static final Cuadro PUERTA_INTERMEDIA_IZQUIERDA = new Cuadro(Sprite.PUERTA_INTERMEDIA_IZQUIERDA);
	
	public static final Cuadro PUERTA_SUPERIOR_DERECHA = new Cuadro(Sprite.PUERTA_SUPERIOR_DERECHA);
	public static final Cuadro PUERTA_INTERMEDIA_DERECHA = new Cuadro(Sprite.PUERTA_INTERMEDIA_DERECHA);
	
	public static final Cuadro PUERTA_SUPERIOR = new Cuadro(Sprite.PUERTA_SUPERIOR);
	public static final Cuadro PUERTA_INFERIOR = new Cuadro(Sprite.PUERTA_INFERIOR);
	public static final Cuadro OXIDO = new Cuadro(Sprite.OXIDO);
	//public static final Cuadro PUERTA_SUPERIOR_CENTRAL =  = new Cuadro(Sprite.PUERTA_SUPERIOR_CENTRAL);
	public static final Cuadro VENTANA_PARED = new Cuadro(Sprite.VENTANA_PARED);
	public static final Cuadro PARED_SUPERIOR = new Cuadro(Sprite.PARED_SUPERIOR);
	
	//fin de la coleccion
	public Cuadro(Sprite sprite){
		this.sprite= sprite;
	}
	public void mostrar(int x, int y, Pantalla pantalla){
		pantalla.mostrarCuadro(x << 5, y << 5, this);
	}
	
	public boolean solido(){
			return false;
	}
}
