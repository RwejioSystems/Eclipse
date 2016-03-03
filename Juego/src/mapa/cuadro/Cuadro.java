package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

public abstract class Cuadro {
	private int x;
	private int y;
	
	public Sprite sprite;
	//Coleccion de cuadros
	public static final Cuadro ASFALTO=new CuadroAsfalto(Sprite.ASFALTO);
	//fin de la coleccion
	public Cuadro(Sprite sprite){
		this.sprite= sprite;
	}
	public void mostrar(int x, int y, Pantalla pantalla){
	}
	
	public boolean solido(){
			return false;
	}
}
