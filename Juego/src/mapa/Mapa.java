package mapa;

import mapa.cuadro.Cuadro;
import graficos.Pantalla;

public abstract class Mapa {
	protected int ancho;
	protected int alto;
	protected int[] cuadros;
	
	public Mapa(int ancho, int alto){
		this.alto=alto;
		this.ancho= ancho;
		cuadros = new int[ancho*alto];
		generarMapa();
	}
	
	public Mapa(String ruta){
		cargarMapa(ruta);
	}
	
	protected void generarMapa(){
		
	}
	
	private void cargarMapa(String ruta){
		
	}
	
	public void actualizar(){
		
	}
	
	public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla){
		//int oeste = compensacionX / 32;
		pantalla.estableceDiferencia(compensacionX, compensacionY);
		
		int oeste = compensacionX >> 5;
		int este = (compensacionX + pantalla.obteneAncho() + Cuadro.LADO) >> 5;
		int norte= compensacionY >> 5;
		int sur= (compensacionY + pantalla.obteneAlto() + Cuadro.LADO) >> 5;
		
		for(int y=norte; y<sur; y ++){
			for(int x=oeste; x<este; x++){
				obtenCuadro(x, y).mostrar(x, y, pantalla);
			}
		}
	}
	
	public Cuadro obtenCuadro(final int x, final int y){
		if(x<0 || y <0 || x>=ancho || y>=alto){
			return Cuadro.VACIO;
		}
		
		switch(cuadros[x + y * ancho]){
			case 0:
				return Cuadro.ASFALTO;
			default:
				return Cuadro.VACIO;
		}
	}
}
