package mapa;

import mapa.cuadro.Cuadro;
import graficos.Pantalla;
import graficos.Sprite;

public abstract class Mapa {
	protected int ancho;
	protected int alto;
	protected int[] cuadros;
	protected Cuadro[] cuadrosCatalogo;
	
	public Mapa(int ancho, int alto){
		this.alto = alto;
		this.ancho = ancho;
		cuadros = new int[ancho*alto];
		generarMapa();
	}
	
	public Mapa(String ruta){
		cargarMapa(ruta);
		generarMapa();
	}
	
	protected void generarMapa(){
		
	}
	
	protected void cargarMapa(String ruta){
		
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
				//obtenCuadro(x, y).mostrar(x, y, pantalla);
				if(x<0 || y <0 || x>=ancho || y>=alto){
					Cuadro.VACIO.mostrar(x, y, pantalla);
				}else{
					cuadrosCatalogo[x+y*ancho].mostrar(x, y, pantalla);
				}
			}
		}
	}
	public Cuadro obtenerCuadroCatalogo(int posicion){
		return cuadrosCatalogo[posicion];
	}
	
	public int obtenerAncho(){
		return ancho;
	}
//	public Cuadro obtenCuadro(final int x, final int y){
//		if(x<0 || y <0 || x>=ancho || y>=alto){
//			return Cuadro.VACIO;
//		}
//		
//		switch(cuadros[x + y * ancho]){
//			case 0:
//				return Cuadro.ASFALTO;
//			case 1:
//				return Cuadro.ARENA;
//			case 2:
//				return Cuadro.BORDE_CARRETERA;
//			case 3:
//				return Cuadro.CENTRO_CARRETERA;
//			case 4:
//				return Cuadro.ESQUINA_CARRETERA;
//			case 5:
//				return Cuadro.PARED_PIEDRA;
//			case 6:
//				return Cuadro.PARED_PIEDRA_INFERIOR;
//			case 7:
//				return Cuadro.PARED_PIEDRA_CARRETERA;
//			case 8:
//				return Cuadro.PUERTA_SUPERIOR_IZQUIERDA;
//			case 9:
//				return Cuadro.PUERTA_INTERMEDIA_IZQUIERDA;
//			case 10:
//				return Cuadro.PUERTA_INFERIOR;
//			case 11:
//				return Cuadro.OXIDO;
//			case 12:
//				return Cuadro.VENTANA_PARED;
//			case 13:
//				return Cuadro.PARED_SUPERIOR;
//			case 14:
//				return Cuadro.PUERTA_SUPERIOR;
//			default:
//				return Cuadro.VACIO;
//		}
//	}
}
